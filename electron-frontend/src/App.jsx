import React, { useEffect } from 'react';
import {
  Routes,
  Route,
  useLocation
} from 'react-router-dom';

import './css/style.css';
import './charts/ChartjsConfig';

// Import pages Cadastros
import Dashboard from './pages/Dashboard/Dashboard';
import Dados from './pages/Dashboard/Dados';
import Empresas from './pages/Cadastros/Empresas';
import EmpresasAdd from './pages/Cadastros/EmpresasAdd';
import Fornecedores from './pages/Cadastros/Fornecedores';  
import Produtos from './pages/Cadastros/Produtos';
import ProdutosAdd from './pages/Cadastros/ProdutosAdd';
import Serviços from './pages/Cadastros/Serviços';
import Transportadoras from './pages/Cadastros/Transportadoras';
import Vendedores from './pages/Cadastros/Vendedores';
// Import pages Entradas
import Pedidos from './pages/Entradas/Pedidos';
import NotaEntrada from './pages/Entradas/NotaEntrada';
// Import pages Saídas
import Orcamento from './pages/Saidas/Orcamento';
import Pedido from './pages/Saidas/Pedido';
import NotaFiscalNFE from './pages/Saidas/NotaFiscalNFE';
// import pages Financeiros
import Caixa from './pages/Financeiro/Caixa';
// import pages Auxiliares
import Tributacoes from './pages/Auxiliares/Tributacoes';
import RelacoesCFOP from './pages/Auxiliares/RelacoesCFOP';
// import pages Configurações
import Empresa from './pages/Configuracoes/Empresa';
import Estoque from './pages/Configuracoes/Estoque';
import NFE from './pages/Configuracoes/NFE';
import Produto from './pages/Configuracoes/Produto';
// import pages Permissoes
import Cadastros from './pages/Permissoes/Cadastros';
import Entradas from './pages/Permissoes/Entradas';
//import Estoque from './pages/Permissoes/Estoque';
import Financeiro from './pages/Permissoes/Financeiro';
import Relatorios from './pages/Permissoes/Relatorios';
import Saidas from './pages/Permissoes/Saidas';
import Estoque2 from './pages/Permissoes/Estoque2';

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
        <Route exact path="/cadastro/empresas" element={<Empresas/>} />
        <Route exact path="/cadastro/empresas/adicionar" element={<EmpresasAdd/>} />
        <Route exact path="/cadastro/fornecedores" element={<Fornecedores/>} />
        <Route exact path="/cadastro/produtos" element={<Produtos/>} />
        <Route exact path="/cadastro/produtos/adicionar" element={<ProdutosAdd/>} />
        <Route exact path="/cadastro/servicos" element={<Serviços/>} />
        <Route exact path="/cadastro/transportadoras" element={<Transportadoras/>} />
        <Route exact path="/cadastro/vendedores" element={<Vendedores/>} />
        <Route exact path="/entradas/pedidos" element={<Pedidos/>} />
        <Route exact path="/entradas/NotaEntrada" element={<NotaEntrada/>} />
        <Route exact path="/saidas/orcamento" element={<Orcamento/>} />
        <Route exact path="/saidas/pedido" element={<Pedido/>} />
        <Route exact path="/saidas/notafiscalnfe" element={<NotaFiscalNFE/>} />
        <Route exact path="/financeiro/Caixa" element={<Caixa/>} />
        <Route exact path="/auxiliares/tributacoes" element={<Tributacoes/>} />
        <Route exact path="/auxiliares/cfop" element={<RelacoesCFOP/>} /> 
        <Route exact path="/configuracoes/Empresa" element={<Empresa/>} /> 
        <Route exact path="/configuracoes/Estoque" element={<Estoque/>} /> 
        <Route exact path="/configuracoes/NFE" element={<NFE/>} /> 
        <Route exact path="/configuracoes/Produto" element={<Produto/>} /> 

        <Route exact path="/permissoes/Cadastros" element={<Cadastros/>} />
        <Route exact path="/permissoes/Entradas" element={<Entradas/>} />
        <Route exact path="/permissoes/Estoque2" element={<Estoque2/>} />
        <Route exact path="/permissoes/Financeiro" element={<Financeiro/>} />
        <Route exact path="/permissoes/Relatorios" element={<Relatorios/>} />
        <Route exact path="/permissoes/Saidas" element={<Saidas/>} />
      </Routes>
    </>
  );
}

export default App;
