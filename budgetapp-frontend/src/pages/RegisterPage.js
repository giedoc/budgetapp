import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const RegisterPage = () => {
    const [form, setForm] = useState({ username: '', password:''});
    const [error,setError] = useState('')
    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value})
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try{
            const res = await axios.post ('http://localhost:8080/api/auth/register', form);
            localStorage.setItem('token', res.data.token);
            navigate('/')
        }catch (err) {
            setError('Registration Failed!. Try different username.');
        }
    };

    return (
        <div style={{ maxWidth: 400, margin: '0 auto' }}>
            <h2>Register</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:</label>
                    <input name="username" value={form.username} onChange={handleChange} required />
                </div>
                <div style={{ marginTop: 10 }}>
                    <label>Password:</label>
                    <input name="password" type="password" value={form.password} onChange={handleChange} required />
                </div>
                <button style={{ marginTop: 20 }} type="submit">Register</button>
            </form>
        </div>
    );
};

export default RegisterPage;