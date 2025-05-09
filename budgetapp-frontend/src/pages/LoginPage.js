import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [form,setForm] = useState({username:'', password:''});
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value})
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try{
            const res = await axios.post('http://localhost:8080/api/auth/login', form);
            localStorage.setItem('token', res.data.token);
            navigate('/');
        }catch(err) {
            setError('Login failed. Please check your username or password.');
        }
    }

    return (
        <div style={{ maxWidth: 400, margin: '0 auto' }} >
            <h2>Login</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:</label>
                    <input name="username" value={form.username} onChange={handleChange} required></input>
                </div>
                <div style={{ marginTop: 10 }}>
                    <label>Password:</label>
                    <input name='password' type='password' value={form.password} onChange={handleChange} required></input>
                </div>
                <button style={{ merginTop: 20}} type='submit'>Login</button>
            </form>
        </div>
    );
}

export default LoginPage;