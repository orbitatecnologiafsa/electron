import React, { useState } from "react";
import Modal from "./Modal";

const InputWBtn = ({ InputModel, label, btnTitle, options, onSelect, modalTitle }) => {
  const [inputValue, setInputValue] = useState("");

  const handleButtonClick = () => {
    console.log("Botão clicado! Valor do input:", inputValue);
  };

  return (
    <div className="flex items-center">
      <input
        type="text"
        value={inputValue}
        onChange={(e) => setInputValue(e.target.value)}
        className="p-2 border border-gray-300 rounded-md flex-1" // Estilos para o input
      />
      <Modal 
        label={label} 
        btnTitle={btnTitle} 
        options={options} 
        onSelect={onSelect} 
        modalTitle={modalTitle} 
      />
    </div>
  );
};

export default InputWBtn;
