import React, { ReactNode } from 'react';
import { Link } from 'react-router-dom';
import '../../App.css';

interface NavLink {
    path: string;
    label: string;
}

interface SceneLayoutProps {
    title: string;
    navLinks: NavLink[];
    children: ReactNode;
    backgroundImage: string;
}

const SceneLayout: React.FC<SceneLayoutProps> = ({ title, navLinks, children, backgroundImage }) => {
    return (
        <main className="sceneLayoutContainer" style={{backgroundImage: `url(${backgroundImage})`}}>
            <header>
                <h1 className="sceneTitle">{title}</h1>
            </header>

            <nav className="choices">
                {navLinks.map((link) => (
                    <Link key={link.path} to={link.path}>
                        <button className="medievalButton">{link.label}</button>
                    </Link>
                ))}
            </nav>

            <article className="textWindow">
                {children}
            </article>
        </main>
    );
};

export default SceneLayout;
