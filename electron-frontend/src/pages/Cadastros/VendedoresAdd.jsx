import Datepicker from '../../components/Datepicker';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import DropDown from '../../components/DropDown';
import InputWBtn from '../../components/InputWBtn';

const VendedoresAdd = () => {
    
  const [docDigitado, setDocDigitado] = useState('');

  const [documentoValue, setDocumentoValue] = useState('');
  const [cepCnpj, setCepCnpj] = useState('');
  const [tipoDrop, setTipoDrop] = useState('');
  const [baseCal, setBaseCal] = useState('');
  const [cepUf, setUf] = useState('');
  const [tipoPessoa, setTipoPessoa] = useState('');
  const [empresa,setEmpresa] = useState([]);
  const monucipioModal = [{codigo:'1', nome:'São Paulo'},{codigo:'2', nome:'Rio de Janeiro'},{codigo:'3', nome:'Belo Horizonte'}];

  const dropTipoComissao = [
    'Total da venda',
    'Parcela Recebida',
  ];

  const dropBaseCalculo = [
    'Total Líquido (Total + Impostos)',
    'Total Bruto (Preço * Quantidade)',
    'Total (Total Bruto - Descontos + Acréscimos)',
  ];

  const [formsData, setFormsData] = useState({
    tipo: '',
    nomeRazaoSocial: '',
    nomeFantasia: '',
    cpfCnpj: '',
    cep: '',
    nomeExibicao: '',
    municipioId: '',
    uf: '',
    desconto:'',
    comissao:'',
    tipoComissao: '',
    bairro: '',
    logradouro: '',
    numero: '',
    complemento: '',
    email: '',
    telefone: '',
    entidade: '',
    dataDeNascimento: '',
    celular: '',
    contato: '',
    rgInscricaoEstadual: '',
    inscricaoMunicipal: '',
    observacao: '',
    empresaId: '',
  });
  
  const handleMenuItemClick = (item,tipo) => {

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
        setTipoPessoa(item);
    }else if(tipo === 'TipoComissao'){
      if(item === 'Total da venda'){
        formsData.tipoComissao = 'TOTAL_DA_VENDA';
      }else if(item === 'Parcela Recebida'){
        formsData.tipoComissao = 'PARCELA_RECEBIDA';
      }
      setTipoDrop(item);
    }else if(tipo === 'UF'){
        formsData.uf = (item);
        setUf(item);
    }else if(tipo == "BaseCal"){
        if(item === 'Total Líquido (Total + Impostos)'){
            setBaseCal('Total Líquido');
            formsData.baseCalculo = "TOTAL_LIQUIDO";
        }else if(item === 'Total Bruto (Preço * Quantidade)'){
            setBaseCal('Total Bruto');
            formsData.baseCalculo = "TOTAL_BRUTO";
        }else if(item === 'Total (Total Bruto - Descontos + Acréscimos)'){
            setBaseCal('Total');
            formsData.baseCalculo = "TOTAL";
        }
    }else if(tipo === 'Municipio'){
        formsData.municipioId = (item.codigo);
        console.log("Municipio:",formsData.municipioId);
    }else if(tipo === 'Empresa'){
        formsData.empresaId = (item.codigo);
        console.log("Empresa:",formsData.empresaId);
    }
    console.log("Tipo:",tipo);
    console.log("Item:",item);
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

  const handleDateChange = (dates) => {
    // Convert the selected date to ISO string format (yyyy-MM-dd)
    const formattedDate = dates[0].toISOString().split('T')[0];
    formsData.dataDeNascimento = formattedDate;
    console.log("Data selecionada:", formsData.dataDeNascimento);
  };
  const [error, setError] = useState(null);

  const [sidebarOpen, setSidebarOpen] = useState(false);

  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Antes: "+formsData);
    try {
        const response = await axios.post("http://localhost:8080/vendedor", formsData);
        setFormsData({
          tipo: '',
          nomeRazaoSocial: '',
          nomeFantasia: '',
          cpfCnpj: '',
          cep: '',
          nomeExibicao: '',
          municipioId: '',
          uf: '',
          desconto:'',
          comissao:'',
          tipoComissao: '',
          bairro: '',
          logradouro: '',
          numero: '',
          complemento: '',
          email: '',
          telefone: '',
          entidade: '',
          dataDeNascimento: '',
          celular: '',
          contato: '',
          rgInscricaoEstadual: '',
          inscricaoMunicipal: '',
          observacao: '',
          empresaId: '',
        });
    } catch (error) {
        console.error('Erro ao cadastrar empresa:', error.response ? error.response.data : error.message);
    }
  };

  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/cadastro/vendedores');
  };

  const navigate = useNavigate();

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

      // Aplica a formatação conforme o número de caracteres
      if (formattedValue.length <= 10) {
        formattedValue = formattedValue.replace(/^(\d{2})(\d{0,4})(\d{0,4})$/, "($1) $2-$3");
      } else {
        formattedValue = formattedValue.replace(/^(\d{2})(\d{0,5})(\d{0,4})$/, "($1) $2-$3");
      }

      setFormsData((prevData) => ({ ...prevData, [name]: formattedValue }));
    } else {
      setFormsData((prevData) => ({ ...prevData, [name]: value }));
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
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Cadastro de Vendedores</h3>
              
              {/* Dados do Fornecedor */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4 justify-between">

                <div className="flex flex-col mr-4">
                    <DropDown labelDrop={"Tipo de Fornecedor"} title= 'Selecione o fornecedor' ValorBtn={tipoPessoa} listItens={['Fisica', 'Estrangeiro','Juridica']} onSelect={(item) => handleMenuItemClick(item, "Pessoa")} />
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
                  />
                </div>
              </div>

              {/*Nome e fantasia */}

              <div className="flex flex-col md:flex-row gap-4 ">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Vendedor</label>
                  <input
                    type="text"
                    name="entidade"
                    value={formsData.entidade}
                    onChange={handleInputChange}
                    className=" w-[24rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nome/Razão Social</label>
                  <input
                    type="text"
                    name="nomeRazaoSocial"
                    value={formsData.nomeRazaoSocial}
                    onChange={handleInputChange}
                    className=" w-[23.5rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>

                <div className="flex flex-col">
                    <label className="block ml-1 text-sm font-medium leading-6 text-black">Data de nascimento</label>
                    <Datepicker align="center" onDateChange={handleDateChange}/>
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Fantasia</label>
                  <input
                    type="text"
                    name="nomeFantasia"
                    value={formsData.nomeFantasia}
                    onChange={handleInputChange}
                    className=" w-[32rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Empresa</label>
                  <InputWBtn widthValue={29.5} options={empresa} modalTitle="Escolha a empresa" onSelect={(item) => handleMenuItemClick(item,'Empresa')}/>
                </div>
                
              </div>
              
              {/* Aréa da Registro*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">RG- Registro Geral</label>
                  <input
                    type="text"
                    name="inscricaoMunicipal"
                    value={formsData.inscricaoMunicipal}
                    onChange={handleInputChange}
                    className="w-[32rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">IE - Inscrição Estadual</label>
                  <input
                    type="text"
                    name="rgInscricaoEstadual"
                    value={formsData.rgInscricaoEstadual}
                    onChange={handleInputChange}
                    maxLength={documentoValue === 'CPF' ? 14 : 18}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
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
                  />
                </div>
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Celular</label>
                  <input
                    type="text"
                    name="celular"
                    value={formsData.celular}
                    onChange={handleInputChange}
                    className="w-[21rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Telefone</label>
                  <input
                    type="text"
                    name="telefone"
                    value={formsData.telefone}
                    onChange={handleInputChange}
                    className="w-[22rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
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
                    className="w-[24rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Logradouro</label>
                  <input
                    type="text"
                    name="logradouro"
                    value={formsData.logradouro}
                    onChange={handleInputChange}
                    className="w-[25rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                    <DropDown labelDrop="UF" title= 'Selecione a UF' ValorBtn={cepUf} listItens={["US", "MX", "BA"]} onSelect={(item) => handleMenuItemClick(item,'UF')} />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Numero</label>
                  <input
                    type="text"
                    name="numero"
                    value={formsData.numero}
                    onChange={handleInputChange}
                    className="w-[32rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Municipio</label>
                  <InputWBtn widthValue={30} options={monucipioModal} modalTitle="Escolha o Municipio" onSelect={(item) => handleMenuItemClick(item,'Municipio')}/>
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
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Complemento</label>
                  <input
                    type="text"
                    name="complemento"
                    value={formsData.complemento}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              {/*Dados do veículo*/}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Valores
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do veículo*/}
              <div className="flex flex-col md:flex-row gap-4 justify-between ">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Desconto(%)</label>
                  <input
                    type="text"
                    name="desconto"
                    value={formsData.desconto}
                    onChange={handleInputChange}
                    className="w-[15rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Comissão(%)</label>
                  <input
                    type="text"
                    name="comissao"
                    value={formsData.comissao}
                    onChange={handleInputChange}
                    className="w-[14rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>

                <div className="flex flex-col ">
                    <DropDown labelDrop="Tipo de Comissão" title='Selecione o tipo de comissão' ValorBtn={tipoDrop} listItens={dropTipoComissao} onSelect={(item) => handleMenuItemClick(item,'TipoComissao')}/>
                </div>

                <div className="flex flex-col ">
                  <DropDown labelDrop="Base de Calculo" title="Selecione a base de calculo" ValorBtn={baseCal} listItens={dropBaseCalculo} onSelect={(item) => handleMenuItemClick(item,'BaseCal')}/>
                </div>
              </div>

              {/*Observação*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Observação</label>
                <textarea
                        type="text"
                        name="observacao"
                        value={formsData.observacao}
                        onChange={handleInputChange}
                        className="w-[67rem] h-[45px] resize-none px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
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

export default VendedoresAdd;