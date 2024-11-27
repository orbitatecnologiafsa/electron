import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../../partials/Sidebar';
import Header from '../../../partials/Header';
import axios from 'axios';
import Datepicker from '../../../components/Datepicker';
import DropDown from '../../../components/DropDown';
import TabelaWBtn from '../../../components/TabelaWBtn';
import InputWBtn from '../../../components/InputWBtn';

const PedidoAdd = () => {

  const [opSelected,setOpSelected] = useState('');
  const [opDia,setOpDia] = useState('');
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const modalOptions = [
    { id: 1, name: "Laranja", quantidade: 1, preco: 9.30},
    { id: 2, name: "Enchaguante", quantidade: 1, preco: 12.32},
    { id: 3, name: "Vassoura", quantidade: 1, preco: 10.65 },
  ];

  const [formData, setFormData] = useState({
    Fornecedor: '',
    emissao: '',
    trasnportadora: '',
    previsao: '',
    produto: '',
    totalPedido:'',
    desconto:'',
    NParcela: '',
    OpVcto: '',
    dia:'',
    peso:'',
    observacao: '',
  });
  
  const dropOp= [
    'Dia fixo',
    'Intervalo'
  ];
  
  const dropDia = [
    "US", "MX", "BA",
  ]

  const dropAtv = [
    "programação", "Processamento de dados", "Analise e desenvolvimento de sistemas",
  ]
  
  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/empresas-proprietarias/", formData);
        setFormData({
          Fornecedor: '',
          emissao: '',
          trasnportadora: '',
          previsao: '',
          produto: '',
          totalPedido:'',
          NParcela: '',
          OpVcto: '',
          dia:'',
          peso:'',
          desconto:'',
          observacao: '',
        });
    } catch (error) {
        console.error('Erro ao cadastrar empresa:', error.response ? error.response.data : error.message);
    }
  };

  {/*Itens do Menu */}
  const handleMenuItemClick = (item, index) => {
    if (index == "OP"){
      setOpSelected(item);
      formData.OpVcto = (item);
    }else if( index == "DIA"){
      setOpDia(item);
      formData.dia = (item);
    }else if( index == "Atividade"){
      setAtvSelected(item);
      formData.atividade = (item);
    }
  };

  const onSelectedOptionModal = (option,tipo) => {
    if(tipo=='Fornecedor'){
      formData.Fornecedor = option.id;
    }else if(tipo=='Transportadora'){
      formData.trasnportadora = option.id;
    }else if(tipo == 'Produto'){
      formData.produto = option.id;
    }
  }

  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/entradas/pedidos');
  };
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleDateChange = (dates,tipo) => {
    if(tipo == 'Emissao'){
      formData.emissao = dates[0].toLocaleDateString('pt-BR');
    }else if(tipo == 'Previsao'){
      formData.previsao = dates[0].toLocaleDateString('pt-BR');
    }
  };

  const optionModalF = [{id:'1',name:'Froz da mota', ceep:'12345-678'},{id:'2',name:'FELIX Amaral', ceep:'12215-628'}];
  const optionModalT = [{id:'1',name:'Moto', ceep:'12345-678'},{id:'2',name:'BUS', ceep:'12215-628'}];
  const optionDia= ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31']

  {/* Tela principal do administrador */}
  return (
    <div className="flex h-screen overflow-hidden">

      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

        {/* Conteúdo */}
        <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
          <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
          <div className="flex justify-center w-full">
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full  h-[65rem]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Adicionar Pedido</h3>

              {/* Dados do produto */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados do Pedido de Compra
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                    <label className="block ml-1 text-sm font-medium leading-6 text-black">Emissão</label>
                    <Datepicker align="center" onDateChange={handleDateChange('Emissao')}/>
                </div>
                <div className="flex flex-col">
                    <label className="block ml-1 text-sm font-medium leading-6 text-black">Previsão</label>
                    <Datepicker align="center" onDateChange={handleDateChange('Previsao')}/>
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block text-sm font-medium leading-6 text-black">Fornecedor</label>
                  <InputWBtn widthValue={29} options={optionModalF} onSelect={onSelectedOptionModal('Fornecedor')} modalTitle="Escolha um fornecedor"/>
                </div>
                <div className="flex flex-col">
                  <label className="block text-sm font-medium leading-6 text-black">Transportadora</label>
                  <InputWBtn widthValue={29} options={optionModalT} onSelect={onSelectedOptionModal('Transportadora')} modalTitle="Escolha uma transportadora"/>
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                  <TabelaWBtn modalOptions={modalOptions}></TabelaWBtn>
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Peso</label>
                  <input
                    type="text"
                    name="peso"
                    value={formData.peso}
                    onChange={handleInputChange}
                    className="w-[20rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Desconto</label>
                  <input
                    type="text"
                    name="desconto"
                    value={formData.desconto}
                    onChange={handleInputChange}
                    className="w-[20rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Total Pedido(R$)</label>
                  <input
                    type="text"
                    name="totalPedido"
                    value={formData.totalPedido}
                    onChange={handleInputChange}
                    className="w-[20rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>
              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                    <DropDown labelDrop="Opção Vcto" title={"Selecione a opção"} ValorBtn={opSelected} listItens={dropOp} onSelect={(item) => handleMenuItemClick(item,'OP')}/>
                </div>
                <div className="flex flex-col">
                  {opSelected === "Dia fixo" ? (
                    <div className="flex flex-col">
                      <DropDown labelDrop="Dia" title={"Selecione os dias"} ValorBtn={opDia} listItens={optionDia} onSelect={(item) => handleMenuItemClick(item,'DIA')}/>
                    </div>
                  ) : (
                    <div className="flex flex-col">
                      <label className="block ml-1 text-sm font-medium leading-6 text-black">Intervalo</label>
                      <input
                        type="text"
                        name="dia"
                        value={formData.dia}
                        onChange={handleInputChange}
                        className="w-[14rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                      />
                    </div>
                  )}
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nº Parcelas</label>
                  <input
                    type="text"
                    name="NParcela"
                    value={formData.NParcela}
                    onChange={handleInputChange}
                    className="w-[20rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Observação</label>
                  <textarea
                          type="text"
                          name="observacao"
                          value={formData.observacao}
                          onChange={handleInputChange}
                          className="w-[66rem] h-[45px] resize-none px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
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

export default PedidoAdd;