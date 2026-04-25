import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Distritos from './pages/Distritos';
import RutaProtegida from "./components/RutaProtegida.jsx";
import Iglesias from './pages/Iglesias';
import Pastores from './pages/Pastores';


function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>
                    
                    <Route path="/login" element={<Login />} />

                    <Route path="/dashboard" element={
                        <RutaProtegida rolesPermitidos={["ADMIN", "AP", "PD", "PASTOR"]}>
                            <Dashboard />
                        </RutaProtegida>
                    } />

                    <Route path="/distritos" element={
                        <RutaProtegida rolesPermitidos={["ADMIN", "AP", "PD"]}>
                            <Distritos />
                        </RutaProtegida>
                    } />

                    <Route
                      path="/iglesias"
                      element={
                        <RutaProtegida rolesPermitidos={['ADMIN', 'AP', 'PD', 'PASTOR']}>
                          <Iglesias />
                        </RutaProtegida>
                      }
                    />

                    <Route
                      path="/pastores"
                      element={
                        <RutaProtegida rolesPermitidos={['ADMIN', 'AP', 'PD']}>
                          <Pastores />
                        </RutaProtegida>
                      }
                    />

                </Routes>
            </BrowserRouter>
        </AuthProvider>
    );
}

export default App;