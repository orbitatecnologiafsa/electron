//InputWBtn.jsx
import React, { useState } from "react";
import Modal from "./Modal";
import InputGroup from 'react-bootstrap/InputGroup';

const InputWBtn = ({ widthValue, options, modalTitle, onSelect }) => {
  const [inputValue, setInputValue] = useState(""); // Estado para controlar o valor do input

  // Função para atualizar o input quando uma opção é selecionada no modal
  const handleSelect = (option) => {
    setInputValue(option.name); // Atualiza o valor do input com o nome da opção selecionada
    onSelect(option); // Chama a função onSelect do componente pai
  };

  return (
    <div className="flex items-center">
      <InputGroup className="mb-3">
        <input
          type="text"
          value={inputValue} // O valor do input é controlado por inputValue
          onChange={(e) => setInputValue(e.target.value)} // Altera o valor do input manualmente
          className="w-[20rem] h-12 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
          style={{ width: `${widthValue}rem` }} 
        />
        <Modal
          options={options} 
          onSelect={handleSelect} // Passa a função handleSelect para o modal
          modalTitle={modalTitle} 
        />
      </InputGroup>
    </div>
  );
};

export default InputWBtn;
