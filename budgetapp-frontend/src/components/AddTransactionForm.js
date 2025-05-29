import React, { useState } from "react";

import axios from "axios";

const AddTransactionForm = ({onSuccess}) => {
    const[form, setForm] = useState({
        amount: '',
        type: 'income',
        description: '',
        date: ''
    });

    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value})
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        const token = localStorage.getItem('token');

        try{
            await axios.post('http://localhost:8080/api/transactions', form, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setForm({ amount: '', type:'income', description:'', date:'' })
            if (onSuccess) onSuccess();
        } catch (err) {
            alert('failed to add transaction')
        } 
    }

    return ( 
        <form onSubmit={handleSubmit} style={{ maxWidth: 400, marginBottom: 30 }}>
             <h3>Add Transaction</h3>
             <div>
                <label>Amount:</label>
                <input type="number" name="amount" value={form.amount} onChange={handleChange}/>
             </div>

        </form>
    )

}