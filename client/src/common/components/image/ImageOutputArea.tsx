interface ImageOutputAreaProps {
    imageUrl: string;
    size: 's' | 'm' | 'l';
}

const sizeClass = {
    s: 'w-[200px] h-[200px]',
    m: 'w-[400px] h-[400px]',
    l: 'w-[600px] h-[600px]',
}


const ImageOutputArea = ({
    imageUrl,
    size
}: ImageOutputAreaProps) => {
    return (
        <div className="ml-[24px] mt-2 mb-4">
            <img
                src={imageUrl}
                className="rounded border border-gray-300"
            />
        </div>
    );
};

export default ImageOutputArea;