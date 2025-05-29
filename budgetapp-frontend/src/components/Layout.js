import React from 'react';

const Layout = ({children}) => {
    return (
        <div>
      <header style={{ backgroundColor: '#eee', padding: 10 }}>
        <h2>Budget App</h2>
      </header>

      <main style={{ padding: 20 }}>
        {children}
      </main>

      <footer style={{ backgroundColor: '#eee', padding: 10, marginTop: 50 }}>
        <p>© 2025 Budget App</p>
      </footer>
    </div>
    );
}

export default Layout;