import React from 'react';
import { Link } from 'react-router-dom';
import './SceneOnePage.css';
import "../../App.css";

const SceneOnePage: React.FC = () => {
    return (
        <main className="sceneOnePageContainer">
            <header>
                <h1 className="startPageTitle">Szene Eins - Berge</h1>
            </header>

            <nav className="choices">
                <Link to="/hoele"><button className="medievalButton">In die Höhle gehen</button></Link>
                <Link to="/bergpfad"><button className="medievalButton">Den Bergpfad weiter verfolgen</button></Link>
            </nav>

            <article className="textWindow">
                <p>Nachdem du den eisigen Bergpfad entlang gewandert bist, entdeckst du eine mysteriöse Höhle in der Ferne. Der Pfad führt dich direkt an ihrem Eingang vorbei, der von dicken Eiszapfen umrahmt ist. Große Fußabdrücke im Schnee deuten auf die Anwesenheit von Yetis hin und wecken deine Neugier - was könnte sich in dieser Höhle verbergen?</p>
                <p>Du stehst nun vor einer Entscheidung:</p>
                <ul>
                    <li>In die Höhle gehen: Mutig entschließt du dich, die Höhle zu betreten und die Geheimnisse zu enthüllen, die sich im Inneren verbergen. Was wirst du in den tiefen, eisigen Schatten finden?</li>
                    <li>Den Bergpfad weiter verfolgen: Du entscheidest dich, auf dem Pfad zu bleiben und weiter den Berg hinauf zu wandern. Vielleicht führt er dich zu weiteren Entdeckungen oder bietet dir einen besseren Überblick über diese geheimnisvolle, vereiste Landschaft.</li>
                </ul>
                <p>Wähle weise, denn jede Entscheidung wird deinen Weg in diesem Abenteuer prägen. Dein Schicksal liegt in deinen Händen!</p>
            </article>
        </main>
    );
};


export default SceneOnePage;
