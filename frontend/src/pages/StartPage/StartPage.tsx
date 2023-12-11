import React from 'react';
import SceneLayout from '../../components/SceneLayout/SceneLayout';
import backgroundImage from '../../assets/Start.png';
import { useLocation } from "react-router-dom";
import useSaveGameState from "../../hooks/useSaveGameState";

const StartPage: React.FC = () => {
    const location = useLocation();
    useSaveGameState(location.pathname);

    const navLinks = [
        { path: "/berge", label: "Gehe zu den Bergen" },
        { path: "/schloss", label: "Gehe zum Schloss" },
        { path: "/unbekannt", label: "Gehe ins Unbekannte" }
    ];

    return (
        <SceneLayout
            title="Szene Start - Dorf"
            navLinks={navLinks}
            backgroundImage={backgroundImage}
        >
            <p>Aktuelle Location: {location.pathname}</p>
            <p>Hallo Held, willkommen in dieser Visual Novel! Dein Abenteuer beginnt in einem kleinen Dorf, das der
                Ausgangspunkt für deine aufregende Reise ist. Hier hast du die Möglichkeit, dich in ein unvergessliches
                Abenteuer zu stürzen.</p>
            <p>Die Frage ist: Wo möchtest du hingehen?</p>
            <p>Über den oben angezeigten Button kannst du deine Wahl treffen:</p>
            <ul>
                <li>In die Berge: Wage dich in das eisige Terrain, wo rätselhafte Eiskreaturen ihr Unwesen treiben. Ein
                    Ort voller Geheimnisse und Herausforderungen erwartet dich.
                </li>
                <li>Ins Schloss: Besuche das majestätische Schloss, in dem ein Graf residiert. Entdecke, welche Intrigen
                    und Geschichten sich hinter diesen mächtigen Mauern verbergen.
                </li>
                <li>Ins Unbekannte: Oder traust du dich, einen ganz anderen Weg einzuschlagen und dich ins Unbekannte zu
                    begeben? Unvorhersehbare Abenteuer und Überraschungen warten auf dich.
                </li>
            </ul>
            <p>Wähle weise, denn jede Entscheidung wird deinen Weg in diesem Abenteuer prägen. Dein Schicksal liegt in
                deinen Händen!</p>
        </SceneLayout>
    );
};

export default StartPage;
