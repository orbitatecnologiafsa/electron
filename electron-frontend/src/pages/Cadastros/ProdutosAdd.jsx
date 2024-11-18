import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';
import DropDown from '../../components/DropDown';


function ProdutosAdd() {

  const [docDigitado, setDocDigitado] = useState('');

  const [documentoValue, setDocumentoValue] = useState('');
  const [cep, setCep] = useState('');
  const [formsData, setFormsData] = useState({
    index: "",
    codigo: '',
    nome: '',
    quantidade: '',
    classificacao: '',
    unEntrada: '',
    unSaida: '',
    UnTributacao: '',
    tControle: '',
    bloqueada: '',
    disponivel: '',
    precoCusto: '',
    custoMedio: '',
    precoVenda: '',
    precoRevenda: '',
    descricao: '',
    grupo: '',
    ncm: '',
    cest: '',
    tribEstadual: '',
    tribFederal: '',
  });
  const [UnSelected, setUnSelected] = useState("");
  const [Classified, setClassified] = useState("");
  const [UnEntrada, setUnEntrada] = useState("");
  const [UnSaida, setUnSaida] = useState("");
  const [UnTributacao, setUnTributacao] = useState("");
  const [TControle, setTControle] = useState("");

  const handleMenuItemClick = (item, index) => {

    if (index == "UN"){

      setUnSelected(item);
      if(item == "KILOGRAMA"){
        formsData.un = ("KG_KILOGRAMA");
      }else if(item == "LITRO"){
        formsData.un = ("LT_LITRO");
      }else{
        formsData.un = ("MT_METRO");
      }

    }else if( index == "Classficacao"){

      setClassified(item);
      formsData.classificacao = (item);

    }else if( index == "UnEntrada"){

      setUnEntrada(item);
      formsData.unEntrada = (item);

    }else if( index == "UnSaída"){

      setUnSaida(item);
      formsData.unSaida = (item);
      
    }else if(index == 'UnTributacao'){

      setUnTributacao(item);
      formsData.UnTributacao = (item);

    }else if(index == 'TControle'){

      setTControle(item);
      if(item == 'Controla grade'){

        formsData.tControle = ("Controla_Grade");

      }else if(item == 'Vende Francionado'){

        formsData.tControle = ("Vende_Fracionado");
      }else{

        formsData.tControle = (item);
        
      }
    }
  }

  const [sidebarOpen, setSidebarOpen] = useState(false);
  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/produtos/89", formsData);
        setFormsData({
          index: "",
          codigo: '',
          nome: '',
          quantidade: '',
          classificacao: '',
          unEntrada: '',
          unSaida: '',
          UnTributacao: '',
          tControle: '',
          bloqueada: '',
          disponivel: '',
          precoCusto: '',
          custoMedio: '',
          precoVenda: '',
          precoRevenda: '',
          descricao: '',
          grupo: '',
          ncm: '',
          cest: '',
          tribEstadual: '',
          tribFederal: '',
        });
    } catch (error) {
        console.log(formsData);
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
    setFormsData((prevData) => {
      return { ...prevData, [name]: value };
    });
  };
  

const unidadesEmbList = ["KILOGRAMA", "LITRO", "METRO"];

const ClassificacaoList = ["Despesa", "Imobilizado", "Produto"];

const unidadeSaidaEntradaTributação = ["AM","AMPOLA","AR","BALDE","BANDEJ","BARRA","BISNAG","BLOCO","CART","CD","CENTO","CJ","CM","CM2","CX","DISP","DZ","FARDO","FRASCO","GAL","GALÃO","GF","GR","HR","JOGO","K","KG","KIT","LATAO","LT","M2","M3","MC","MI","MILHEI","ML","MT","ND","PACOTE","PARES","PC","RESMA","ROLO","SACO","SACOLA","ST","TAMBOR","TON","TUBO","UN"];

const tipoControle = [ "Composição", "Controla grade", "Pesável","Vende Francionado"];

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
                    checked={formsData.ativo}
                    onChange={() => setFormsData({ ...formsData, ativo: !formsData.ativo })}
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
                    name="codigo"
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
                    className=" w-[23rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
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

                <div className="flex flex-col">
                  <DropDown labelDrop={"Classificação"} title={"Selecione a classificação"} ValorBtn={Classified} listItens={ClassificacaoList} onSelect={(item) => handleMenuItemClick(item,"Classficacao")}/>
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

              <div className="flex flex-col md:flex-row gap-4 justify-between ">

                <div className="flex flex-col">
                  <DropDown labelDrop={"Tipo de controle"} title={"Selecione o tipo de controle"} ValorBtn={TControle} listItens={tipoControle} onSelect={(item) => handleMenuItemClick(item,"TControle")}/>
                </div>

                <div className="flex flex-col">
                  <DropDown labelDrop={"Unidade de Entrada"} title={"Selecione a Un. entrada"} ValorBtn={UnEntrada} listItens={unidadeSaidaEntradaTributação} onSelect={(item) => handleMenuItemClick(item,"UnEntrada")}/>
                </div>

                <div className="flex flex-col">
                  <DropDown labelDrop={"Unidade de Saída"} title={"Selecione a Un. saída"} ValorBtn={UnSaida} listItens={unidadeSaidaEntradaTributação} onSelect={(item) => handleMenuItemClick(item,"UnSaída")}/>
                </div>

                <div className="flex flex-col">
                  <DropDown labelDrop={"Unidade de Tributação"} title={"Selecione a Un. Tributação"} ValorBtn={UnTributacao} listItens={unidadeSaidaEntradaTributação} onSelect={(item) => handleMenuItemClick(item,"UnTributacao")}/>
                </div>
              </div>
              
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Quantidade
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa da Quantidade*/}
              <div className="flex flex-col md:flex-row gap-4  justify-between">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade</label>
                  <input
                    type="text"
                    name="quantidade"
                    value={formsData.quantidade}
                    onChange={handleInputChange}
                    maxLength={documentoValue === 'CPF' ? 14 : 18}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                   <DropDown labelDrop={"Unidade de Medida"} title={"Selecione a unidade"} ValorBtn={UnSelected} listItens={unidadesEmbList} onSelect={(item) => handleMenuItemClick(item,"UN")}/>

                </div>
              </div>

              {/* Aréa do Qauntidade 2*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade bloqueada</label>
                  <input
                    type="text"
                    name="bloqueada"
                    value={formsData.bloqueada}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade disponível</label>
                  <input
                    type="text"
                    name="disponivel"
                    value={formsData.disponivel}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
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