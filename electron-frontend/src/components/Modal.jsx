import React, { useState } from "react";
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

function ModalWithOptions({ options, onSelect, modalTitle }) {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState(""); 

  const handleOptionClick = (option) => {
    setSelectedOption(option.name); // Exibe o nome da opção selecionada
    onSelect(option); // Chama a função onSelect que foi passada para atualizar o input
    setIsOpen(false); // Fecha o modal
  };

  const columnHeaders = options.length > 0 ? Object.keys(options[0]) : [];

  return (
    <div className="flex flex-col">
      <Button 
        variant="outline-secondary" 
        id="button-addon2" 
        className="h-[3rem] w-[3rem] w-40 px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700"
        onClick={() => setIsOpen(true)}
      >
        <FontAwesomeIcon icon={faMagnifyingGlass} style={{color: "#ffffff",}} />
      </Button>

      {isOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
          <div className="bg-white p-4 rounded-md text-center w-96 max-w-full max-h-[80vh] overflow-hidden relative z-60">
            <h2 className="text-lg font-semibold mb-4">{modalTitle}</h2>
            <div className="flex justify-between border-b-2 py-2 mb-2 font-bold">
              {columnHeaders.map((header, index) => (
                <div key={index} className="w-1/2 text-center">
                  {header.charAt(0).toUpperCase() + header.slice(1)}
                </div>
              ))}
            </div>

            <ul className="list-none p-0 m-0 max-h-60 overflow-y-auto">
              {options.map((option) => (
                <li
                  key={option.id}
                  className="cursor-pointer flex justify-between items-center py-2 px-3 hover:bg-gray-100"
                  onClick={() => handleOptionClick(option)}
                >
                  {columnHeaders.map((header, index) => (
                    <div key={index} className="w-1/2 text-center">
                      {option[header]}
                    </div>
                  ))}
                </li>
              ))}
            </ul>

            <button
              className="h-[3rem] w-40 px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700 mt-4"
              onClick={() => setIsOpen(false)}
            >
              Fechar
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default ModalWithOptions;
