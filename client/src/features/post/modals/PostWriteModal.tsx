import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { useState } from "react";
import CommonModal from "../../../common/modals/CommonModal";
import Button from "../../../common/components/button/Button";
import { createPost } from "../apis/PostApi";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import Input from "../../../common/components/input/Input";

interface PostWriteProps {
    onClose: () => void;
}

type PostCreateDto = {
    title: string;
    content: string;
};


function PostWrite({ onClose }: PostWriteProps) {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");

    const handleCreate = async () => {
        if (!title.trim() || !content.trim()) {
            alert("제목과 내용을 모두 입력해주세요.");
            return;
        }

        const dto: PostCreateDto = {
            title,
            content,
        };

        try {
            await createPost(dto);
            onClose();
        } catch (err) {
            printErrorInfo(err);
        }
    };

    return (
        <CommonModal size="l" onClose={onClose}>
            <ModalTitle title={"게시글 작성"} />
            <Input
                size="l"
                label="제목"
                placeholder="제목을 입력하세요"
                disabled={false}
                value={title}
                setValue={setTitle} type={"text"}            />

            <TextArea
                size="l"
                label="내용"
                placeholder="내용을 입력하세요"
                disabled={false}
                value={content}
                setValue={setContent}
            />

            <div className="flex justify-end mr-6">
                <Button
                    color="theme"
                    size="s"
                    disabled={false}
                    text="작성"
                    type="button"
                    onClick={handleCreate}
                />
            </div>
        </CommonModal>
    );
}

export default PostWrite;
