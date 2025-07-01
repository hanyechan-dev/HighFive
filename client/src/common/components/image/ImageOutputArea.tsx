interface ImageOutputAreaProps {
    size: 's' | 'm' | 'l';
    imageUrl: string;
}
const sizeClass = {
    s: 'w-[20px]',
    m: 'w-[464px]',
    l: 'w-[952px]'
}

const ImageOutputArea = ({
    size,
    imageUrl
}: ImageOutputAreaProps) => {

    return (
        <div className={`min-h-[100px] ${sizeClass[size]} border border-gray-300 rounded-lg ml-[24px] mb-[24px] overflow-hidden`} >
            <img
                src={`http://192.168.0.121:8090${imageUrl}`}
                className="block w-full h-auto object-fill" 
            />
        </div>
    )


}

export default ImageOutputArea;


