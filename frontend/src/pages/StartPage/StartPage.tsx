import React from 'react';
import { Link } from 'react-router-dom';
import './StartPage.css';

const StartPage: React.FC = () => {
    return (
        <main className="startPageContainer">
            <header>
                <h1 className="startPageTitle">Szene Eins</h1>
            </header>

            <nav className="choices">
                <Link to="/berge"><button className="medievalButton">Gehe zu den Bergen</button></Link>
                <Link to="/burg"><button className="medievalButton">Gehe zur Burg</button></Link>
                <Link to="/unbekannt"><button className="medievalButton">Gehe ins Unbekannte</button></Link>
            </nav>

            <article className="textWindow">
                <p>Hallo Held, willkommen in dieser Visual Novel! Dein Abenteuer beginnt in einem kleinen Dorf, das der Ausgangspunkt für deine aufregende Reise ist. Hier hast du die Möglichkeit, dich in ein unvergessliches Abenteuer zu stürzen.</p>
                <p>Die Frage ist: Wo möchtest du hingehen?</p>
                <p>Über den oben angezeigten Button kannst du deine Wahl treffen:</p>
                <ul>
                    <li>In die Berge: Wage dich in das eisige Terrain, wo rätselhafte Eiskreaturen ihr Unwesen treiben. Ein Ort voller Geheimnisse und Herausforderungen erwartet dich.</li>
                    <li>Ins Schloss: Besuche das majestätische Schloss, in dem ein Graf residiert. Entdecke, welche Intrigen und Geschichten sich hinter diesen mächtigen Mauern verbergen.</li>
                    <li>Ins Unbekannte: Oder traust du dich, einen ganz anderen Weg einzuschlagen und dich ins Unbekannte zu begeben? Unvorhersehbare Abenteuer und Überraschungen warten auf dich.</li>
                </ul>
                <p>Wähle weise, denn jede Entscheidung wird deinen Weg in diesem Abenteuer prägen. Dein Schicksal liegt in deinen Händen!</p>
            </article>
        </main>
    );
};

export default StartPage;
