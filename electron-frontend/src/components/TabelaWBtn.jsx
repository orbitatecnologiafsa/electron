//TabelaWBtn.jsx
import React, { useState } from "react";
import InputWBtn from "./InputWBtn";

const TabelaWBtn = ({ modalOptions, onSelect }) => {
  const [dados, setDados] = useState([
    { name: "", quantidade: 1, precoUnitario: 0, preco: 0 }, // Inicializando com precoUnitario 0
  ]);

  const handleSelectOption = (option) => {
  if (!option || !Object.values(option)[1] || !option.preco) {
    console.error("Invalid option selected:", option);
    return;
  }

    setDados((prevDados) => [
      ...prevDados.slice(0, -1),
      { 
        name: Object.values(option)[1], 
        quantidade: 1, 
        precoUnitario: option.preco, 
        preco: option.preco, 
      },
      { name: "", quantidade: 1, precoUnitario: 0, preco: 0 },
    ]);

    onSelect('Produto',option.codigo);
  };


  const handleDeleteRow = (index) => {
    if (dados.length === 1) {
      setDados([{ name: "", quantidade: 1, precoUnitario: 0, preco: 0 }]);
    } else {
      setDados((prevDados) => prevDados.filter((_, i) => i !== index));
    }
  };

  const handleQuantidadeChange = (e, index) => {
    const newQuantidade = parseFloat(e.target.value);
  
    if (isNaN(newQuantidade) || newQuantidade <= 0) return; // Impede valores não numéricos ou negativos
  
    setDados((prevDados) => {
      const newData = [...prevDados];
      const produto = newData[index];
      produto.quantidade = newQuantidade;
      produto.preco = produto.precoUnitario * newQuantidade;
      return newData;
    });
  };  

  return (
    <div className="container mx-auto p-4 max-h-[64rem] overflow-y-auto">
      <table className="divide-y min-w-full table-auto border-collapse max-h-[12rem] border border-black">
        <thead className="disabled:bg-gray-300 bg-indigo-600 text-white border border-black">
          <tr className="border border-black">
            <th className="border border-gray-800 px-4 py-2">Código</th>
            <th className="border border-gray-800 px-4 py-2">Nome</th>
            <th className="border border-gray-800 px-4 py-2">Quantidade</th>
            <th className="border border-gray-800 px-4 py-2">Valor unitário</th>
            <th className="border border-gray-800 px-4 py-2">Total</th>
            <th className="border border-gray-800 px-4 py-2">[X]</th>
          </tr>
        </thead>
        <tbody className="border border-black">
          {dados.map((linha, index) => (
            <tr key={index}>
              <td className="border border-gray-800 px-4 py-2">{index + 1}</td>
              <td className="border border-gray-800 px-4 py-2">
                <InputWBtn
                  widthValue={9}
                  options={modalOptions}
                  onSelect={handleSelectOption}
                  modalTitle="Escolha um produto"
                />
              </td>
              <td className="border border-gray-800 px-4 py-2">
                <input
                  type="number"
                  className="w-full px-2 py-1 border border-gray-800 rounded-md"
                  value={linha.quantidade}
                  onChange={(e) => handleQuantidadeChange(e, index)} 
                  min="1"
                />
              </td>
              <td className="border border-gray-800 px-4 py-2">
                <input
                  type="text"
                  className="w-full px-2 py-1 border border-gray-800 rounded-md"
                  value={linha.preco.toFixed(2)}
                  readOnly
                />
              </td>
              <td className="border px-4 py-2">
                <input
                  type="text"
                  className="w-full px-2 py-1 border"
                  value={linha.preco}  // Corrigido para 'preco'
                  onChange={(e) =>
                    setDados((prevDados) => {
                      const newData = [...prevDados];
                      newData[index].preco = e.target.value;  // Corrigido
                      return newData;
                    })
                  }
                />
              </td>
              <td className="border border-gray-800 px-4 py-2">
                <button
                  onClick={() => handleDeleteRow(index)}
                  className="text-red-500 hover:text-red-700"
                >
                  [X]
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TabelaWBtn;
