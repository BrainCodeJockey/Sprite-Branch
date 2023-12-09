import React from 'react';
import SceneLayout from '../../components/SceneLayout/SceneLayout.tsx';
import backgroundImage from '../../assets/sceneOne.png';
import { useLocation } from 'react-router-dom';

const SceneOnePage: React.FC = () => {
    const navLinks = [
        { path: "/hoele", label: "In die Höhle gehen" },
        { path: "/bergpfad", label: "Den Bergpfad weiter verfolgen" }
    ];

    const location = useLocation();

    return (
        <SceneLayout
            title="Szene Eins - Berge"
            navLinks={navLinks}
            backgroundImage={backgroundImage}
        >
            <p>Aktuelle Location: {location.pathname}</p>
            <p>Nachdem du den eisigen Bergpfad entlang gewandert bist, entdeckst du eine mysteriöse Höhle in der Ferne.
                Der Pfad führt dich direkt an ihrem Eingang vorbei, der von dicken Eiszapfen umrahmt ist. Große
                Fußabdrücke im Schnee deuten auf die Anwesenheit von Yetis hin und wecken deine Neugier - was könnte
                sich in dieser Höhle verbergen?</p>
            <p>Du stehst nun vor einer Entscheidung:</p>
            <ul>
                <li>In die Höhle gehen: Mutig entschließt du dich, die Höhle zu betreten und die Geheimnisse zu
                    enthüllen, die sich im Inneren verbergen. Was wirst du in den tiefen, eisigen Schatten finden?
                </li>
                <li>Den Bergpfad weiter verfolgen: Du entscheidest dich, auf dem Pfad zu bleiben und weiter den Berg
                    hinauf zu wandern. Vielleicht führt er dich zu weiteren Entdeckungen oder bietet dir einen besseren
                    Überblick über diese geheimnisvolle, vereiste Landschaft.
                </li>
            </ul>
            <p>Wähle weise, denn jede Entscheidung wird deinen Weg in diesem Abenteuer prägen. Dein Schicksal liegt in
                deinen Händen!</p>
        </SceneLayout>
    );
};

export default SceneOnePage;
