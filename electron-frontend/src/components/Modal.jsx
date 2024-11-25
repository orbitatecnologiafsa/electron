import React, { useState } from "react";

function ModalWithOptions({ label, btnTitle, options, onSelect, modalTitle }) {
  const [isOpen, setIsOpen] = useState(false); // Controle do modal
  const [selectedOption, setSelectedOption] = useState(""); // Opção selecionada

  const handleOptionClick = (option) => {
    setSelectedOption(option.name); // Exibe o nome da opção selecionada
    onSelect(option); // Chama a função onSelect com o objeto completo
    setIsOpen(false); // Fecha o modal
  };
  const buttonWidth = selectedOption === 'Status' ? 'w-44' : 'w-56';

  // Obter as chaves do primeiro item do array de opções para definir as colunas dinamicamente
  const columnHeaders = options.length > 0 ? Object.keys(options[0]) : [];

  return (
    <div className="flex flex-col">
      <label className="block text-sm font-medium leading-6 text-black">{label}</label>
      {/* Botão para abrir o modal */}
      <button
        className={`truncate rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50 h-11 overflow-hidden ${buttonWidth}`}
        onClick={() => setIsOpen(true)}
      >
        {selectedOption || btnTitle}
      </button>

      {/* Modal */}
      {isOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-4 rounded-md text-center w-96 max-w-full max-h-[80vh] overflow-hidden">
            <h2 className="text-lg font-semibold mb-4">{modalTitle}</h2>

            {/* Cabeçalhos de colunas dinâmicos */}
            <div className="flex justify-between border-b-2 py-2 mb-2 font-bold">
              {columnHeaders.map((header, index) => (
                <div key={index} className="w-1/2 text-center">
                  {header.charAt(0).toUpperCase() + header.slice(1)}
                </div>
              ))}
            </div>

            {/* Lista de opções com rolagem se necessário */}
            <ul className="list-none p-0 m-0 max-h-60 overflow-y-auto">
              {options.map((option) => (
                <li
                  key={option.id} // Usando 'id' como chave, que é mais robusto
                  className="cursor-pointer flex justify-between items-center py-2 px-3 hover:bg-gray-100"
                  onClick={() => handleOptionClick(option)}
                >
                  {columnHeaders.map((header, index) => (
                    <div key={index} className="w-1/2 text-center">
                      {option[header]} {/* Exibe o valor da propriedade dinâmica */}
                    </div>
                  ))}
                </li>
              ))}
            </ul>

            <button
              className="h-[3rem] w-40 px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700"
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
