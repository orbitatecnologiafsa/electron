import React, { useCallback } from 'react';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function DropDown({ labelDrop, ValorBtn, title, listItens = [], onSelect }) {
    const handleSelect = useCallback((item) => {
        onSelect(item, 'UFV');
    }, [onSelect]);

    // Determina a largura do botão com base no valor de ValorBtn
    const buttonWidth = title === 'Status' ? 'w-44' : 'w-56';

    return (
        <div className="flex flex-col">
            <label className="block text-sm font-medium leading-6 text-black">{labelDrop}</label>
            <Menu as="div" className="flex rounded-md">
                <div>
                    <MenuButton 
                        className={`truncate rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50 h-11 overflow-hidden ${buttonWidth}`}
                    >
                        {ValorBtn || title}
                    </MenuButton>
                </div>
                <MenuItems
                    transition
                    className="absolute z-10 mt-11 w-56 max-h-60 overflow-auto origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none"
                >
                    <div className="py-1">
                        {listItens.map(item => (
                            <MenuItem key={item}>
                                <a
                                    className="block px-4 py-2 text-sm text-gray-700"
                                    onClick={() => handleSelect(item)}
                                >
                                    {item}
                                </a>
                            </MenuItem>
                        ))}
                    </div>
                </MenuItems>
            </Menu>
        </div>
    );
}

export default DropDown;
