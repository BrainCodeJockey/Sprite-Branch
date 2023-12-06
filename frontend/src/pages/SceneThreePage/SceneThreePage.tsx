import React from 'react';
import { Link } from 'react-router-dom';
import './SceneThreePage.css';
import "../../App.css";

const SceneThreePage: React.FC = () => {
    return (
        <main className="sceneThreePageContainer">
            <header>
                <h1 className="startPageTitle">Szene Drei - Unbekannt</h1>
            </header>

            <nav className="choices">
                <Link to="/hoele"><button className="medievalButton">Den Ast ergreifen</button></Link>
                <Link to="/goal"><button className="medievalButton">Nach den Skelettknochen greifen</button></Link>
            </nav>

                <article className="textWindow">
                    <p>Nachdem du durch den nebligen Sumpf gewandert bist, passiert es: Du fällst plötzlich in das Moor und landest neben einem halb versunkenen Skelett und einem sehr alten Baum. Ein abgebrochener Ast ragt über das Moor hinaus, während die gruselige Atmosphäre durch das düstere Licht und die nebeligen Schatten verstärkt wird.</p>
                    <p>Du stehst nun vor einer dringenden Entscheidung:</p>
                    <ul>
                        <li>Den Ast ergreifen: In einem Versuch, dich aus dem Moor zu befreien, greifst du nach dem abgebrochenen Ast, der über dir hängt. Wird es stabil genug sein, um dich zu retten?</li>
                        <li>Nach den Skelettknochen greifen: Du entscheidest dich, nach den Knochen des halb versunkenen Skeletts zu greifen. Vielleicht bieten sie dir den Halt, den du brauchst, um dich aus dieser misslichen Lage zu befreien.</li>
                    </ul>
                    <p>Wähle schnell, denn jede Entscheidung könnte über dein Schicksal in diesem nebligen und unheimlichen Moor bestimmen!</p>
                </article>
        </main>
    );
};


export default SceneThreePage;
