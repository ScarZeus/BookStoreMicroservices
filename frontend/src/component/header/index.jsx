import './styles.css';
import { FaSearch } from "react-icons/fa";
import crownSvg from "../../assets/crown-svgrepo-com.svg"
import profileSvg from "../../assets/profile.svg"

export default function Header(){
    return(
        <div className="header">
            <h1>
                BookNest
            </h1>
            <div className="navigation">
            <div className='search-bar'>
                    <input type='text' placeholder='search here' />
                    <FaSearch size={20}/>
                    </div>
                <ul>
                    <li>Home</li>
                    <li>library</li>
                    <li><div className='subscription'><img src={crownSvg} id='header-svg'/></div></li>
                    <li><div className='profile'><img src={profileSvg} id='header-svg'/></div></li>
                </ul>
            </div>
        </div>
    );
}