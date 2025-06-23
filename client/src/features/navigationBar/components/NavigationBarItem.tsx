import { Link } from "react-router-dom";

interface NavigationBarItem {
    title: string
    to: string
}

const NavigationBarItem = ({ title, to }: NavigationBarItem) => {
    return (
        <Link to={to}>
            <div className="ml-2 mr-[50px] hover:text-theme cursor-pointer">
                {title}
            </div>
        </Link>
    )
}

export default NavigationBarItem;