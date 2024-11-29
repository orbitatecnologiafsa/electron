import React, { useEffect, useState } from "react";
import Modal from "./Modal";
import InputGroup from 'react-bootstrap/InputGroup';

const InputWBtn = ({ widthValue, options, modalTitle, onSelect, tipo }) => {
  const [inputValue, setInputValue] = useState("");

  const handleSelect = (option) => {
    // Aqui, selecionamos o segundo valor de `option` (no caso, vamos supor que seja o segundo valor da linha).
    const secondValue = Object.values(option)[1]; // Pega o segundo valor da linha.
    
    console.log('secondValue: ', secondValue);
    
    setInputValue(secondValue);

    if (typeof onSelect === 'function') {
      if (tipo) {
        console.log('onSelect is a function 2');
        onSelect(tipo, option.codigo); // Exemplo de quando você precisa passar a `codigo`
      } else {
        console.log(option);
        onSelect(option); // Passa o objeto inteiro
      }
    } else {
      console.error('onSelect is not a function');
    }
  };

  useEffect(() => {
    // Você pode adicionar lógica aqui se necessário
  }, [inputValue]);

  return (
    <div className="flex items-center">
      <InputGroup className="mb-3">
        <input
          type="text"
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
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
