
import React, { useState } from 'react';
import axios from 'axios';
import './Login.css';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [strength, setStrength] = useState('');
    const [error, setError] = useState('');
    const [suggestedPassword, setSuggestedPassword] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const validateEmail = (email) => {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(String(email).toLowerCase());
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!validateEmail(email)) {
            setError('Invalid email format');
            return;
        }

        try {
            const strengthResponse = await axios.post('http://localhost:8080/api/password/validate', { password });
            setStrength(strengthResponse.data);

            if (strengthResponse.data === 'Strong') {
                const response = await axios.post('http://localhost:8080/api/users/signup', { email, password });
                console.log('Signup successful:', response.data);
                setSuccessMessage('Registered successfully');
                setError('');
            } else {
                setError('Password is not strong enough.');
                setSuccessMessage('');
            }
        } catch (error) {
            setError('Signup error');
            setSuccessMessage('');
            console.error('Error during signup:', error);
        }
    };

    const handleSuggestPassword = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/password/suggest');
            setSuggestedPassword(response.data);
        } catch (error) {
            setError('Error suggesting password');
            console.error('Error suggesting password:', error);
        }
    };

    return (
        <div className="login-container">
            <h2>Password Strength Checker</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Email:</label>
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                </div>
                <div className="button-container">
                    <button type="submit">Check</button>
                </div>
                {error && <p className="error">{error}</p>}
                {successMessage && <p className="success">{successMessage}</p>}
                {strength && strength !== 'Strong' && (
                    <button type="button" className="suggest-button" onClick={handleSuggestPassword}>Suggest Strong Password</button>
                )}
                {suggestedPassword && (
                    <p>Suggested Password: {suggestedPassword}</p>
                )}
            </form>
        </div>
    );
};

export default Login;
