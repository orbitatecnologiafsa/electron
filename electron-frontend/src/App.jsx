import React, { useEffect } from 'react';
import {
  Routes,
  Route,
  useLocation
} from 'react-router-dom';

import './css/style.css';
import './charts/ChartjsConfig';

// Import pages Cadrastros
import Dashboard from './pages/Dashboard/Dashboard';
import Dados from './pages/Dashboard/Dados';
import Clientes from './pages/Cadastros/Clientes';
import Fornecedores from './pages/Cadastros/Fornecedores';  
import Produtos from './pages/Cadastros/Produtos';
import Serviços from './pages/Cadastros/Serviços';
import Transportadoras from './pages/Cadastros/Transportadoras';
import Vendedores from './pages/Cadastros/Vendedores';
// Import pages Entradas
import Pedidos from './pages/Entradas/Pedidos';
import Montagem from './pages/Entradas/Montagem';
import NotaEntrada from './pages/Entradas/NotaEntrada';
import MDE from './pages/Entradas/MDE';
// Import pages Entradas
import CupomFiscal from './pages/Saidas/CupomFiscal';
import Faturamento from './pages/Saidas/Faturamento';
import NotaServico from './pages/Saidas/NotaServico';
import MDFE from './pages/Saidas/MDFE';
import Orcamento from './pages/Saidas/Orcamento';
import Pedido from './pages/Saidas/Pedido';
import PreVenda from './pages/Saidas/PreVenda';

function App() {

  const location = useLocation();

  useEffect(() => {
    document.querySelector('html').style.scrollBehavior = 'auto'
    window.scroll({ top: 0 })
    document.querySelector('html').style.scrollBehavior = ''
  }, [location.pathname]); // triggered on route change

  return (
    <>
      <Routes>
        <Route exact path="/" element={<Dashboard/>} />
        <Route exact path="/dados" element={<Dados/>} />
        <Route exact path="/cadastro/clientes" element={<Clientes/>} />
        <Route exact path="/cadastro/fornecedores" element={<Fornecedores/>} />
        <Route exact path="/cadastro/produtos" element={<Produtos/>} />
        <Route exact path="/cadastro/serviços" element={<Serviços/>} />
        <Route exact path="/cadastro/transportadoras" element={<Transportadoras/>} />
        <Route exact path="/cadastro/vendedores" element={<Vendedores/>} />
        <Route exact path="/entradas/pedidos" element={<Pedidos/>} />
        <Route exact path="/entradas/montagem" element={<Montagem/>} />
        <Route exact path="/entradas/NotaEntrada" element={<NotaEntrada/>} />
        <Route exact path="/entradas/MDE" element={<MDE/>} />
        <Route exact path="/saidas/cupomFiscal" element={<CupomFiscal/>} />
        <Route exact path="/saidas/faturamento" element={<Faturamento/>} />
        <Route exact path="/saidas/NotaServico" element={<NotaServico/>} />
        <Route exact path="/saidas/MDFE" element={<MDFE/>} />
        <Route exact path="/saidas/orcamento" element={<Orcamento/>} />
        <Route exact path="/saidas/pedido" element={<Pedido/>} />
        <Route exact path="/saidas/preVenda" element={<PreVenda/>} />
       
      


        
      </Routes>
    </>
  );
}

export default App;
