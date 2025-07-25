interface ImagePreviewAreaProps {
    size: 's' | 'm' | 'l';
    imageUrl: string;
}
const sizeClass = {
    s: 'w-[20px]',
    m: 'w-[464px]',
    l: 'w-[952px]'
}

const ImagePreviewArea = ({
    size,
    imageUrl
}: ImagePreviewAreaProps) => {

    return (
        <div className={`min-h-[100px] ${sizeClass[size]} border border-gray-300 rounded-lg ml-[24px] mb-[24px] overflow-hidden`} >
            <img
                src={`${imageUrl}`}
                className="block w-full h-auto object-fill" 
            />
        </div>
    )


}

export default ImagePreviewArea;


