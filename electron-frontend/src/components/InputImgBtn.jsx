import React, { useState } from "react";
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFolderOpen, faTrash } from '@fortawesome/free-solid-svg-icons';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image';
import Row from 'react-bootstrap/Row';
import WebcamCapture from "./WebcamCapture";

const InputImgBtn = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [imageSrc, setImageSrc] = useState("");
    const [isOpenPhoto, setIsOpenPhoto] = useState(false);

    // Função para lidar com a seleção da imagem
    const handleImageChange = (event) => {
        const file = event.target.files[0];
        console.log(file)
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setImageSrc(reader.result); // Atualiza o estado com a URL da imagem
            };
            reader.readAsDataURL(file);
        }
    };

    const handleImageDelete = ()=> {
        setImageSrc(null);
    };

    return (
        <div>
            <Container>
                <Row>
                    <Col xs={6} md={12} style={{ width: '200px', height: '200px' }}> {/* Definindo tamanho fixo para o container */}
                        <Image 
                            src={imageSrc || "https://via.placeholder.com/171x180"} 
                            thumbnail 
                            style={{ 
                                width: '100%', 
                                height: '100%', 
                                objectFit: 'contain' // Ajusta a imagem sem distorcer, mas mantendo o aspecto
                            }}
                        />
                    </Col>
                </Row>
            </Container>

            <div className="flex flex-col md:flex-row gap-4">
                <Button 
                    variant="outline-secondary" 
                    id="button-addon2" 
                    className="h-[2.75rem] w-[3rem] px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700"
                    onClick={() => setIsOpen(true)}
                >
                    <FontAwesomeIcon icon={faFolderOpen} style={{ color: "#ffffff" }} />
                </Button>
                
                <Button 
                    variant="outline-secondary" 
                    id="button-addon2" 
                    className="h-[2.75rem] w-[3rem] px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700"
                    onClick={() => handleImageDelete()} 
                >
                    <FontAwesomeIcon icon={faTrash} style={{ color: "#ffffff" }} />
                </Button>

            </div>

            {isOpen && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
                    <div className="bg-white p-4 rounded-md text-center w-[900px] max-w-full max-h-[180vh] overflow-hidden relative z-60">
                        <h2 className="text-lg font-semibold mb-4">Selecione uma imagem</h2>
                        {/* Input para selecionar a imagem */}
                        <input 
                            type="file" 
                            id="file" 
                            name="file" 
                            accept="image/*" 
                            onChange={handleImageChange} 
                        />
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
};

export default InputImgBtn;
