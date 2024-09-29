import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { LoginPage } from './pages/LoginPage';
import { RegisterPage } from './pages/RegisterPage';
import { SuccessPage } from './pages/SuccessPage';
import { HomePage } from "./pages/HomePage";
import { Toaster } from './components/ui/toaster';

// App.js ou App.tsx
function App() {

  return (
    <Router>
      <Toaster />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/registro" element={<RegisterPage />} />
        <Route path="/sucesso" element={<SuccessPage />} />
      </Routes>
    </Router>
  );
}

export default App;
