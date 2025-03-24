import './styles.css';
import { FaSearch } from "react-icons/fa";


export default function Header(){
    return(
        <div className="header">
            <h1>
                BookNest
            </h1>
            <div className="navigation">
            <div className='search-bar'>
                    <input type='search' placeholder='search here' />
                    <FaSearch />
                    </div>
                <ul>
                    <li>Home</li>
                    <li>library</li>
                    <li>subsctibe</li>
                    <li>profile</li>
                </ul>
            </div>
        </div>
    );
}