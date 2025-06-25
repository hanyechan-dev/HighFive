import { Link } from "react-router-dom";

interface NavigationBarItem {
    title: string
    to: string
    dropDown?: { title: string; to: string }[]
}

const NavigationBarItem = ({ title, to, dropDown }: NavigationBarItem) => {

    if (dropDown) {
        return (
            <div className="relative group ml-[50px] cursor-pointer">
                <div className="hover:text-theme">{title}</div>
                <div className="absolute top-full left-0 z-10 hidden group-hover:block bg-white border rounded-lg shadow-lg min-w-[150px]">
                    {dropDown.map((item, index) => (
                        <Link
                            key={index}
                            to={item.to}
                            className="block px-4 py-2 text-sm text-gray-700 hover:bg-semi_theme"
                        >
                            {item.title}
                        </Link>
                    ))}
                </div>
            </div>
        )
    } else {
        return (
            <Link to={to}>
                <div className="ml-[50px] hover:text-theme cursor-pointer">
                    {title}
                </div>
            </Link>
        )
    }


}

export default NavigationBarItem;