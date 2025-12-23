/* Shared client-side validation helpers for PAMS */
(function(){
    const patterns = {
        email: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
        phone: /^[6-9]\d{9}$/,
        password: /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=[\]{};':"\\|,.<>\/\?]).{8,}$/
    };

    function setError(el, message){
        if(!el) return;
        const err = document.getElementById(el.id + '-error');
        if(message){
            el.classList.add('input-invalid');
            if(err) err.textContent = message;
        } else {
            el.classList.remove('input-invalid');
            if(err) err.textContent = '';
        }
    }

    function validateName(el){
        if(!el || !el.value || el.value.trim().length === 0){ setError(el, 'Please enter your name'); return false; }
        setError(el, ''); return true;
    }

    function validateEmail(el){
        if(!el || !el.value || !patterns.email.test(el.value.trim())){ setError(el, 'Please enter a valid email'); return false; }
        setError(el, ''); return true;
    }

    function validatePhone(el){
        if(!el || !el.value || !patterns.phone.test(el.value.trim())){ setError(el, 'Phone must be 10 digits and start with 6,7,8, or 9'); return false; }
        setError(el, ''); return true;
    }

    function validatePassword(el, allowEmpty){
        if(!el) return true;
        if(allowEmpty && (!el.value || el.value.trim().length === 0)){ setError(el, ''); return true; }
        if(!el.value || !patterns.password.test(el.value)){ setError(el, "Password doesn't meet requirements"); return false; }
        setError(el, ''); return true;
    }

    function attachFormValidation(options){
        // options: { formId, fields: {nameId?, emailId?, phoneId?, passwordId?, allowEmptyPassword } }
        const form = document.getElementById(options.formId);
        if(!form) return;

        const name = options.fields.nameId ? document.getElementById(options.fields.nameId) : null;
        const email = options.fields.emailId ? document.getElementById(options.fields.emailId) : null;
        const phone = options.fields.phoneId ? document.getElementById(options.fields.phoneId) : null;
        const password = options.fields.passwordId ? document.getElementById(options.fields.passwordId) : null;

        const validate = function(){
            let ok = true;
            if(name) ok = validateName(name) && ok;
            if(email) ok = validateEmail(email) && ok;
            if(phone) ok = validatePhone(phone) && ok;
            if(password) ok = validatePassword(password, !!options.fields.allowEmptyPassword) && ok;
            return ok;
        };

        [name, email, phone, password].forEach(el => { if(el) el.addEventListener('input', validate); });
        form.addEventListener('submit', function(e){ if(!validate()) e.preventDefault(); });
    }

    // Auto-attach to known forms if present
    document.addEventListener('DOMContentLoaded', function(){
        attachFormValidation({ formId: 'registerForm', fields: { nameId: 'patientName', emailId: 'email', phoneId: 'phno', passwordId: 'password', allowEmptyPassword: false } });
        attachFormValidation({ formId: 'loginForm', fields: { phoneId: 'login-phno', passwordId: 'login-password', allowEmptyPassword: false } });
        attachFormValidation({ formId: 'profileForm', fields: { nameId: 'patientName', emailId: 'email', phoneId: 'phno', passwordId: 'password', allowEmptyPassword: true } });
    });

    // expose helpers for manual use if needed
    window.PAMSValidation = { validateName, validateEmail, validatePhone, validatePassword, attachFormValidation };
})();
