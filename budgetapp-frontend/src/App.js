import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import HomePage from './pages/HomePage';
import ProtectedRoute from './components/ProtectedRoute';
import Layout from './components/Layout';
import TransactionsPage from './pages/TransactionsPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={
          <ProtectedRoute> 
            <Layout> <HomePage/> </Layout>
          </ProtectedRoute>} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path ="/transactions" element={<ProtectedRoute>
          <Layout> 
            <TransactionsPage /> 
            </Layout>
            </ProtectedRoute>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
