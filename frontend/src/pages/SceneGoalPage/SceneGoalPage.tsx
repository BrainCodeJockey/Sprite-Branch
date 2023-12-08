import React from 'react';
import SceneLayout from '../../components/SceneLayout/SceneLayout.tsx';
import backgroundImage from '../../assets/sceneGoal.png';
import {useLocation} from "react-router-dom";

interface NavLink {
    path: string;
    label: string;
}

const SceneGoalPage: React.FC = () => {
    const navLinks: NavLink[] = [];

    const location = useLocation();

    return (
        <SceneLayout
            title="Szene Goal - Gewonnen"
            navLinks={navLinks}
            backgroundImage={backgroundImage}
        >

            <p>Aktuelle Location: {location.pathname}</p>
            <p>Im nebelverhangenen Sumpf, wo du zuvor einen Skelettarm entdeckt hattest, fandest du dich plötzlich
                feststeckend wieder. In einem Moment der Entschlossenheit ergriffst du den Arm, und überraschenderweise
                zog diese Handlung dich aus deiner misslichen Lage. Der Goldtopf in den Händen des Skeletts, unerwartet,
                markierte einen unvergesslichen Wendepunkt in deinem Abenteuer.</p>
        </SceneLayout>
    );
};

export default SceneGoalPage;
