import type { ReactNode } from "react";

interface Content {
    id: number;
    [key: string]: any;
}

interface ListProps {
    checkable: boolean;
    titleList: string[];
    contentList: Content[];
    additionalAction?: ReactNode;
}

function List({
    checkable,
    titleList,
    contentList,
    additionalAction,
}: ListProps) {
    const defaultSetting = "font-roboto text-base mb-2";

    return (
            <div>
                {titleList.map((title) => (
                    <span className={defaultSetting}>
                        {title}
                    </span>
                ))}
            </div>
            {contentList.map((content) => (
                <div key={content.id} >
                    {/* content 객체를 여기서 가공해서 보여줘야 함 */}
                    {JSON.stringify(content)}
                </div>
            ))}

            {additionalAction}
    );
}

export default List;


// <div className="flex items-center gap-2">
//     {checkable && (
//         <Checkbox
//             checked={false}
//             setChecked={(value) => {
//                 console.log("check toggled:", value);
//             }}
//         />
//     )}
// </div>