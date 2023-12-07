import React from 'react';
import './SceneOnePage.css';
import "../../App.css";

const SceneGoalPage: React.FC = () => {
    return (
        <main className="goalPageContainer">
            <header>
                <h1 className="startPageTitle">Szene Goal - Gewonnen</h1>
            </header>


            <article className="textWindow">
                <p>Im nebelverhangenen Sumpf, wo du zuvor einen Skelettarm entdeckt hattest, fandest du dich plötzlich feststeckend wieder. In einem Moment der Entschlossenheit ergriffst du den Arm, und überraschenderweise zog diese Handlung dich aus deiner misslichen Lage. Der Goldtopf in den Händen des Skeletts, unerwartet, markierte einen unvergesslichen Wendepunkt in deinem Abenteuer.</p>
            </article>
        </main>
    );
};


export default SceneGoalPage;
