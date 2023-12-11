import { useState } from 'react';
import { Link } from 'react-router-dom';
import './Ready.css';
import '../../App.css';

const characterSelectionPath = '/character-selection';

export default function Ready() {
    const [isHovered, setIsHovered] = useState(false);
    const zeldaButton = 'src/assets/zelda_button.svg';


    const handleMouseEnter = () => {
        setIsHovered(true);
    };

    const handleMouseLeave = () => {
        setIsHovered(false);
    };

    return (
        <section className="ready-container">
            <div className="background-image"></div>
            <div className="clouds">
                {Array.from({ length: 5 }, (_, index) => (
                    <div className={`cloud x${index + 1}`} key={index}></div>
                ))}
            </div>
            <div className="content">
                <h1 className="zelda-title">Sprite-Branch</h1>
                <Link to={characterSelectionPath}>
                    <button
                        onMouseEnter={handleMouseEnter}
                        onMouseLeave={handleMouseLeave}
                        className={`zelda-button ${isHovered ? 'hovered' : ''}`}
                        aria-label="Play"
                    >
                        <img src={zeldaButton} alt="Play" />
                    </button>
                </Link>
            </div>
        </section>
    );
}
