import React, { useState } from "react";
import InputWBtn from "./InputWBtn";

const TabelaWBtn = ({ modalOptions }) => {
  const [dados, setDados] = useState([
    { name: "", quantidade: 1, precoUnitario: 0, preco: 0 }, // Inicializando com precoUnitario 0
  ]);

  // Função para selecionar um produto e adicionar a linha com o nome e preço
  const handleSelectOption = (option) => {
    setDados((prevDados) => [
      ...prevDados.slice(0, -1),
      { 
        name: option.name, 
        quantidade: 1, 
        precoUnitario: option.preco, // Armazenando o preco unitario
        preco: option.preco, // Inicializando o preco com o precoUnitario
      },
      { name: "", quantidade: 1, precoUnitario: 0, preco: 0 }, // Nova linha em branco
    ]);
  };

  // Função para excluir ou limpar a linha
  const handleDeleteRow = (index) => {
    if (dados.length === 1) {
      // Se for a última linha, apenas limpar os valores
      setDados([{ name: "", quantidade: 1, precoUnitario: 0, preco: 0 }]);
    } else {
      // Caso contrário, excluir a linha
      setDados((prevDados) => prevDados.filter((_, i) => i !== index));
    }
  };

  // Função para atualizar a quantidade e recalcular o preço
  const handleQuantidadeChange = (e, index) => {
    const newQuantidade = e.target.value;

    if (isNaN(newQuantidade) || newQuantidade <= 0) return; // Impede valores não numéricos ou negativos

    setDados((prevDados) => {
      const newData = [...prevDados];
      const produto = newData[index];
      produto.quantidade = newQuantidade;
      produto.preco = produto.precoUnitario * newQuantidade; // Calculando o novo preço
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
