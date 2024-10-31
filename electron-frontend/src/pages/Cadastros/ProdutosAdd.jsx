import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';


function ProdutosAdd() {

  const [docDigitado, setDocDigitado] = useState('');

  const [documentoValue, setDocumentoValue] = useState('');
  const [cep, setCep] = useState('');
  const [UnSelected, setUnSelected] = useState("");

  const [formsData, setFormsData] = useState({
    index: "",
    codigo: '',
    barras: '',
    nome: '',
    un: '',
    quantidade: '',
    qtdBloqueada: '',
    qtdDisponivel: '',
    qtdIdeal: '',
    precoCusto: '',
    custoMedio: '',
    precoVenda: '',
    precoRevenda: '',
    descricao: '',
    contrLote: '',
    contrSerial: '',
    contrGrade: '',
    grupo: '',
    ncm: '',
    cest: '',
    tribEstadual: '',
    tribFederal: '',
    referencia: '',
    status: '',
  });

  const [formData, setFormData] = useState({
    nomeRazao: '',
    fantasia: '',
    documento: '',
    cep: '',
    municipio: '',
    uf: '',
    bairro: '',
    logradouro: '',
    numero: '',
    complemento: '',
    cpfCnpj: '',
    pfOuPj: '',
    email: '',
    telefone: '',
    celular: '',
    contato: '',
    rgInscricaoEstadual: '',
    inscricaoEstadualMunicipal: '',
    observacao: '',
    ativo: true,
    revenda: false,
  });

  const handleMenuItemClick = (item, index) => {
    if (index == "UN"){
      setUnSelected(item);
      formsData.un = (item);
    }else if( index == "LOTE"){
      setLoteSelected(item);
      formsData.contrLote = (item);
    }else if( index == "SERIAL"){
      setSerialSelected(item);
      formsData.contrSerial = (item);
    }else if( index == "GRADE"){
      setGradeSelected(item);
      formsData.contrGrade = (item);
    }
  }


  const [error, setError] = useState(null);


  const [sidebarOpen, setSidebarOpen] = useState(false);
  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/empresas-proprietarias/", formData);
        setFormsData({
            tipo: '',
            razaoSocial: '',
            nomeFantasia: '',
            cpfCnpj: '',
            cep: '',
            nomeExibicao: '',
            municipio: '',
            uf: '',
            bairro: '',
            logradouro: '',
            numero: '',
            complemento: '',
            email: '',
            telefone: '',
            celular: '',
            contato: '',
            rgInscricaoEstadual: '',
            inscricaoEstadualMunicipal: '',
            observacao: '',
            ativo: true,
        });
    } catch (error) {
        console.error('Erro ao cadastrar empresa:', error.response ? error.response.data : error.message);
    }
  };

  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/cadastro/produtos');
  };
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    setFormsData((prevData) => ({ ...prevData, [name]: value }));
};


  {/* Tela principal do administrador */}
  return (
    <div className="flex h-screen overflow-hidden">

      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

        {/* Conteúdo */}
        <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
          <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
          <div className="flex justify-center w-full">
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full  h-[85rem]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Cadastro de Produto</h3>

                <div className="flex items-center">
                  <input
                    type="checkbox"
                    name="ativo"
                    checked={formData.ativo}
                    onChange={() => setFormData({ ...formData, ativo: !formData.ativo })}
                    className="mr-2 rounded"
                  />
                  <label className="text-base">Ativo</label>
                </div>

              {/* Dados do produto */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Código de barras</label>
                  <input
                    type="text"
                    name="codigoBarras"
                    value={formsData.codigo}
                    onChange={handleInputChange}
                    className=" w-[13rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nome</label>
                  <input
                    type="text"
                    name="nome"
                    value={formsData.nome}
                    onChange={handleInputChange}
                    className=" w-[38rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Grupo</label>
                  <input
                    type="text"
                    name="grupo"
                    value={formsData.grupo}
                    onChange={handleInputChange}
                    className=" w-[13rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>
                
              </div>

              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Descrição</label>
                <textarea
                        type="text"
                        name="descricao"
                        value={formsData.descricao}
                        onChange={handleInputChange}
                        className="w-[66rem] h-[45px] resize-none px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                    />
              </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Quantidade
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa da Quantidade*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade</label>
                  <input
                    type="text"
                    name="quantidade"
                    value={formsData.quantidade}
                    onChange={handleInputChange}
                    maxLength={documentoValue === 'CPF' ? 14 : 18}
                    className="w-[50.8rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Unidade de Medida</label>
                  <Menu as="div" className="flex rounded-md">
                            <div>
                              <MenuButton className="w-56 h-11 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                                {UnSelected || 'Selecione a unidade'}
                              </MenuButton>
                            </div>
                            <MenuItems
                              transition
                              className="absolute z-10 mt-10 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                              <div className="py-1">
                                {["kg","hg", "dag", "g", "dg", "cg", "Mmg", "UF"].map(item => (
                                  <MenuItem key={item}>
                                    <a
                                      className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                                      onClick={() => handleMenuItemClick(item)}
                                    >
                                      {item}
                                    </a>
                                  </MenuItem>
                                ))}
                              </div>
                            </MenuItems>
                   </Menu>
                </div>
              </div>

              {/* Aréa do Qauntidade 2*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade bloqueada</label>
                  <input
                    type="text"
                    name="qtdBloqueada"
                    value={formsData.qtdBloqueada}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade disponível</label>
                  <input
                    type="text"
                    name="qtdDisponivel"
                    value={formsData.qtdDisponivel}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade ideal</label>
                  <input
                    type="text"
                    name="qtdIdeal"
                    value={formsData.qtdIdeal}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Valores
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do Custo*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Custo</label>
                  <input
                    type="text"
                    name="custo"
                    value={formsData.custo}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Custo médio</label>
                  <input
                    type="text"
                    name="custoMedio"
                    value={formsData.custoMedio}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>
              
              {/* Aréa do Preço*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Preço de venda</label>
                  <input
                    type="text"
                    name="precoVenda"
                    value={formsData.precoVenda}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Preço de revenda</label>
                  <input
                    type="text"
                    name="precoRevenda"
                    value={formsData.precoRevenda}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Contr.
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>
              {/* Aréa de Contr*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Contr. Lote</label>
                  <Menu as="div" className="flex rounded-md">
                            <div>
                              <MenuButton className="w-56 h-11 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                                {formsData.contrLote || 'Tem Lote?'}
                              </MenuButton>
                            </div>
                            <MenuItems
                              transition
                              className="absolute z-10 mt-10 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                              <div className="py-1">
                                {["Sim", "Não"].map(item => (
                                  <MenuItem key={item}>
                                    <a
                                      className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                                      onClick={() => handleMenuItemClick(item)}
                                    >
                                      {item}
                                    </a>
                                  </MenuItem>
                                ))}
                              </div>
                            </MenuItems>
                   </Menu>
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Contr. Serial</label>
                  <Menu as="div" className="flex rounded-md">
                            <div>
                              <MenuButton className="w-56 h-11 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                                {formsData.contrSerial || 'Tem Serial?'}
                              </MenuButton>
                            </div>
                            <MenuItems
                              transition
                              className="absolute z-10 mt-10 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                              <div className="py-1">
                                {["Sim", "Não"].map(item => (
                                  <MenuItem key={item}>
                                    <a
                                      className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                                      onClick={() => handleMenuItemClick(item)}
                                    >
                                      {item}
                                    </a>
                                  </MenuItem>
                                ))}
                              </div>
                            </MenuItems>
                   </Menu>
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Contr. Grade</label>
                  <Menu as="div" className="flex rounded-md">
                            <div>
                              <MenuButton className="w-56 h-11 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                                {formsData.contrGrade || 'Tem Grade?'}
                              </MenuButton>
                            </div>
                            <MenuItems
                              transition
                              className="absolute z-10 mt-10 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                              <div className="py-1">
                                {["Sim", "Não"].map(item => (
                                  <MenuItem key={item}>
                                    <a
                                      className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                                      onClick={() => handleMenuItemClick(item)}
                                    >
                                      {item}
                                    </a>
                                  </MenuItem>
                                ))}
                              </div>
                            </MenuItems>
                   </Menu>
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Tributação
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Contato do Cliente */}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">NCM</label>
                  <input
                    type="text"
                    name="ncm"
                    value={formsData.ncm}
                    onChange={handleInputChange}
                    className="w-[44rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">CEST</label>
                  <input
                    type="text"
                    name="cest"
                    value={formsData.cest}
                    onChange={handleInputChange}
                    className="w-[21rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
              </div>
              
              {/* Trib. Estadual*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Trib. Estadual</label>
                  <input
                    type="text"
                    name="tribEstadual"
                    value={formsData.tribEstadual}
                    onChange={handleInputChange}
                    className="w-[66rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
              </div>

              {/* Trib. Federal*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Trib. Federal</label>
                  <input
                    type="text"
                    name="tribFederal"
                    value={formsData.tribFederal}
                    onChange={handleInputChange}
                    className="w-[66rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
              </div>
              <div className="flex justify-end gap-4">
                <button type="submit" className="h-[3rem] w-40 px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700">
                  Cadastrar
                </button>
                <button type="button" className="h-[3rem] w-[10rem] rounded-mdbg-white px-4 py-2 text-sm font-semibold text-gray-900 shadow-sm rounded-md ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                  Copiar
                </button>
                <button type="button" onClick={handleRedirect} className="h-[3rem] w-[10rem] rounded-mdbg-white px-4 py-2 text-sm font-semibold text-gray-900 shadow-sm rounded-md ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                  Voltar
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }

export default ProdutosAdd;