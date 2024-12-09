import React, { useEffect,useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Datepicker from '../../components/Datepicker';
import Header from '../../partials/Header';
import axios from 'axios';
import DropDown from '../../components/DropDown';
import InputWBtn from '../../components/InputWBtn';

function TransportadorasAdd() {
  const [documentoValue, setDocumentoValue] = useState('');
  const [tipoP,setTipoP] = useState('');
  const [cepCnpj, setCepCnpj] = useState('');
  const [valueCnpj, setValueCnpj] = useState('');
  const monucipioModal = [{codigo:'1', nome:'São Paulo'},{codigo:'2', nome:'Rio de Janeiro'},{codigo:'3', nome:'Belo Horizonte'}];
  const [empresa,setEmpresa] = useState([]);

  const [formsData, setFormsData] = useState({
    index: "",
    cpfCnpj: '',
    entidade:'',
    nomeRazaoSocial: '',
    nomeFantasia: '',
    rgInscricaoEstadual: '',
    inscricaoMunicipal: '',
    contato: '',
    cep: '',
    logradouro: '',
    numero: '',
    uf: '',
    municipioId: '',
    bairro: '',
    complemento: '',
    celular: '',
    telefone: '',
    tipo:'',
    email: '',
    dataDeNascimento: '',
    empresaId: '',
    observacoes: '',
    placaVeiculo: '',
    anttVeiculo:'',
  });
  
  const handleMenuItemClick = (item, tipo) => {
    if(tipo === 'Pessoa'){
      setCepCnpj('CPF');
        if(item === 'Fisica'){
            formsData.tipo = ("PESSOA_FISICA");
        }else if(item === 'Juridica'){
            setCepCnpj('CNPJ');
            formsData.tipo = ("PESSOA_JURIDICA");
        }else{
            formsData.tipo = ("ESTRANGEIRO");
        }
        setTipoP(item);
    }
  };

  
  const [errorMessage, setErrorMessage] = useState('');


  const [sidebarOpen, setSidebarOpen] = useState(false);
  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/transportadoras", formsData);
        
        // Se a requisição for bem-sucedida, limpa os dados do formulário
        setFormsData({
            tipo: '',
            nomeRazaoSocial: '',
            nomeFantasia: '',
            cpfCnpj: '',
            cep: '',
            municipioId: '',
            uf: '',
            bairro: '',
            logradouro: '',
            numero: '',
            complemento: '',
            dataDeNascimento: '',
            email: '',
            telefone: '',
            celular: '',
            entidade:'',
            contato: '',
            rgInscricaoEstadual: '',
            inscricaoMunicipal: '',
            observacoes: '',
            empresaId: '',
            placaVeiculo: '',
            anttVeiculo: '',
        });

        // Limpa qualquer mensagem de erro anterior
        setErrorMessage('');
        
    } catch (error) {
      console.log(formsData);
        if (error.response && error.response.data) {
          const errorMessage = error.response.data.message;
    
          if (errorMessage.includes('Cannot coerce empty String to `com.electron.domain.enums.TipoPessoa`')) {
            setErrorMessage('Tipo de pessoa inválido');
          } else {
            setErrorMessage('Erro ao cadastrar Transportadora: ' + errorMessage);
          }
        } else {
          setErrorMessage('Erro desconhecido ao cadastrar Transportadora');
        }
    }
};


  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/cadastro/transportadoras');
  };
  const navigate = useNavigate();

  const handleMunItemClick = (tipo,item) => {
    if(tipo === 'Municiopio'){
        formsData.municipioId = (item);
        console.log("Municipio:",formsData.municipioId);
    }
    if(tipo ==='Empresa'){
      formsData.empresaId = (item);
      console.log("Empresa:",formsData.empresaId);
    }
  };

  const handleDateChange = (dates) => {
    // Convert the selected date to ISO string format (yyyy-MM-dd)
    const formattedDate = dates[0].toISOString().split('T')[0];
    formsData.dataDeNascimento = formattedDate;
    console.log("Data selecionada:", formsData.dataDeNascimento);
  };
  
  const handleInputChange = (e) => {
    const { name, value } = e.target;

    // Se o campo for "celular"
    if (name === "celular" || name === "telefone") {
      // Remove todos os caracteres não numéricos
      let formattedValue = value.replace(/\D/g, "");

      // Limita o número de caracteres a 11 (no formato (XX) XXXXX-XXXX) ou 10 (no formato (XX) XXXX-XXXX)
      if (formattedValue.length > 11) {
        formattedValue = formattedValue.slice(0, 11); // Limita a 11 caracteres
      }

      if (formattedValue.length <= 10) {
        formattedValue = formattedValue.replace(/^(\d{2})(\d{0,4})(\d{0,4})$/, "($1) $2-$3");
      } else {
        formattedValue = formattedValue.replace(/^(\d{2})(\d{0,5})(\d{0,4})$/, "($1) $2-$3");
      }

      setFormsData((prevData) => ({ ...prevData, [name]: formattedValue }));
    } else {
      setFormsData((prevData) => ({ ...prevData, [name]: value }));
    }
    setValueCnpj(value);
  };

  const getCNPJ = async (value) => {
    try {
      const responseCnpj = await axios.get('https://open.cnpja.com/office/' + value);
      console.log(responseCnpj.data);
      // Atualiza os dados no estado
      setFormsData((prevData) => ({
        ...prevData,
        nomeRazaoSocial: responseCnpj.data.company.name,
        nomeFantasia: responseCnpj.data.alias,
        cep: responseCnpj.data.address.zip,
        cpf_cnpj: value,
        numero: responseCnpj.data.address.number,
        email: responseCnpj.data.emails[0]?.address || '',
        telefone: responseCnpj.data.phones[0]?.area + responseCnpj.data.phones[0]?.number || '',
        bairro: responseCnpj.data.address.district,
        logradouro: responseCnpj.data.address.street,
        uf: responseCnpj.data.address.state,
      }));

      const responseUF = await axios.get(''+value)
    } catch (error) {
      console.error('Erro ao buscar dados do CNPJ:', error);
    }
  };

  useEffect(() => {
    // Função para pegar os dados da API
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/empresas-proprietarias');

        let gruposTransformados = response.data.map(item => ({
          codigo: item.id,
          nome: item.razaoSocial
        }));
  
        setEmpresa(gruposTransformados);
  
      } catch (error) {
        console.error('Erro ao buscar os dados', error);
      }
    };
  
    fetchData();
  }, []);

  const handleCnpjBlur = () => {
    if (cepCnpj =='CNPJ') {
      console.log("Entrou:", valueCnpj);
      getCNPJ(valueCnpj);
    }
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
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full h-[80rem]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Cadastro de Transportadora</h3>
              
              {/* Dados do Fornecedor */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col mr-4">
                    <DropDown labelDrop={"Tipo de Fornecedor"} title= 'Selecione o fornecedor' ValorBtn={tipoP} listItens={['Fisica', 'Estrangeiro','Juridica']} onSelect={(item) => handleMenuItemClick(item, "Pessoa")} />
                </div>
                
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">{ cepCnpj ? cepCnpj : 'CPF'}</label>
                  <input
                    type="text"
                    name="cpfCnpj"
                    value={formsData.cpfCnpj}
                    onChange={handleInputChange}
                    className=" w-[20rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    onBlur={handleCnpjBlur}
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Data de nascimento</label>
                  <Datepicker align="center" onDateChange={handleDateChange}/>
                </div>
              </div>

              {/*Nome e fantasia */}

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nome/Razão Social</label>
                  <input
                    type="text"
                    name="nomeRazaoSocial"
                    value={formsData.nomeRazaoSocial}
                    onChange={handleInputChange}
                    className=" w-[32.5rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Fantasia</label>
                  <input
                    type="text"
                    name="nomeFantasia"
                    value={formsData.nomeFantasia}
                    onChange={handleInputChange}
                    className=" w-[32rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              <div className='flex flex-col md:flex-row gap-4'>
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Entidade</label>
                  <input
                    type="text"
                    name="entidade"
                    value={formsData.entidade}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Empresa</label>
                  <InputWBtn widthValue={29} heightValue={2.75} options={empresa} modalTitle="Escolha a Empresa" onSelect={handleMunItemClick} tipo={"Empresa"}/>
                </div>
              </div>

              {/* Aréa da Registro*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">IE - Inscrição Estadual</label>
                  <input
                    type="text"
                    name="rgInscricaoEstadual"
                    value={formsData.rgInscricaoEstadual}
                    onChange={handleInputChange}
                    maxLength={documentoValue === 'CPF' ? 14 : 18}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">IEM - Inscricão Estadual Municipais</label>
                  <input
                    type="text"
                    name="inscricaoMunicipal"
                    value={formsData.inscricaoMunicipal}
                    onChange={handleInputChange}
                    className="w-[32rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Contato
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do Contato*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Contato</label>
                  <input
                    type="text"
                    name="contato"
                    value={formsData.contato}
                    onChange={handleInputChange}
                    className="w-[20rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Celular</label>
                  <input
                    type="text"
                    name="celular"
                    value={formsData.celular}
                    onChange={handleInputChange}
                    className="w-[22rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Telefone</label>
                  <input
                    type="text"
                    name="telefone"
                    value={formsData.telefone}
                    onChange={handleInputChange}
                    className="w-[21rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Email</label>
                  <input
                    type="text"
                    name="email"
                    value={formsData.email}
                    onChange={handleInputChange}
                    className="w-[66rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Endereço
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do endereço*/}
              <div className="flex flex-col md:flex-row gap-4">
                
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">CEP</label>
                  <input
                    type="text"
                    name="cep"
                    value={formsData.cep}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Logradouro</label>
                  <input
                    type="text"
                    name="logradouro"
                    value={formsData.logradouro}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Numero</label>
                  <input
                    type="text"
                    name="numero"
                    value={formsData.numero}
                    onChange={handleInputChange}
                    className="w-[20.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Bairro</label>
                  <input
                    type="text"
                    name="bairro"
                    value={formsData.bairro}
                    onChange={handleInputChange}
                    className="w-[35rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Municipio</label>
                  <InputWBtn widthValue={21} heightValue={2.75} options={monucipioModal} modalTitle="Escolha o Municipio" onSelect={handleMunItemClick} tipo={"Municiopio"}/>
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">UF</label>
                  <input
                    type="text"
                    name="uf"
                    value={formsData.uf}
                    onChange={handleInputChange}
                    className="w-[4rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>

              </div>

              <div className="flex flex-col md:flex-row gap-4">

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Complemento</label>
                  <input
                    type="text"
                    name="complemento"
                    value={formsData.complemento}
                    onChange={handleInputChange}
                    className="w-[66rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              {/*Dados do veículo*/}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Veículo
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do veículo*/}
              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Placa</label>
                  <input
                    type="text"
                    name="placaVeiculo"
                    value={formsData.placaVeiculo}
                    onChange={handleInputChange}
                    className="w-[12rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">ANTT</label>
                  <input
                    type="text"
                    name="anttVeiculo"
                    value={formsData.anttVeiculo}
                    onChange={handleInputChange}
                    className="w-[12rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              {/*Observação*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Observação</label>
                <textarea
                        type="text"
                        name="observacoes"
                        value={formsData.observacoes}
                        onChange={handleInputChange}
                        className="w-[66rem] h-[45px] resize-none px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                        style={{ textTransform: 'uppercase' }}
                    />
              </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="mt-2 text-red-600 text-sm">
                  {errorMessage}
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

export default TransportadorasAdd;