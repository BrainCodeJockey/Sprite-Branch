import React from 'react';
import { Link } from 'react-router-dom';
import './SceneTwoPage.css';
import "../../App.css";

const SceneTwoPage: React.FC = () => {
    return (
        <main className="sceneTwoPageContainer">
            <header>
                <h1 className="startPageTitle">Szene Zwei - Schloss</h1>
            </header>

            <nav className="choices">
                <Link to="/innere"><button className="medievalButton">Weiter gehen ins Innere</button></Link>
                <Link to="/umgebung"><button className="medievalButton">Gehe zur Burg</button></Link>
            </nav>

            <article className="textWindow">
                <p>Nachdem du die Burg betreten hast, erblickst du eine beeindruckende Inneneinrichtung. Die hohen Hallen sind erfüllt mit dem Echo vergangener Zeiten, während du sanft über den kühlen Steinboden schreitest. Von irgendwo aus der Tiefe der Burg dringen leise, doch unheilvolle Geräusche an dein Ohr - ein Gemisch aus fernen, hallenden Rufen und einem scharfen Zischen.</p>
                <p>Du stehst nun vor einer Entscheidung:</p>
                <ul>
                    <li>Weiter gehen ins Innere: Mutig entschließt du dich, tiefer in die Burg vorzudringen, um das Geheimnis hinter den unheimlichen Geräuschen zu lüften. Was verbirgt sich in den Schatten dieser alten Mauern?</li>
                    <li>Die Umgebung absuchen: Du entscheidest dich, die nähere Umgebung zu erkunden. Vielleicht findest du Hinweise, die dir bei deinem Abenteuer helfen oder Licht in die dunklen Ecken dieser geheimnisvollen Burg bringen.</li>
                </ul>
                <p>Wähle weise, denn jede Entscheidung wird deinen Weg in diesem Abenteuer prägen. Dein Schicksal liegt in deinen Händen!</p>
            </article>
        </main>
    );
};


export default SceneTwoPage;
