import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { useState } from "react";
import CommonModal from "../../../common/modals/CommonModal";
import Button from "../../../common/components/button/Button";

interface PostWriteProps {
  onClose: () => void;
}

function PostWrite({ onClose }: PostWriteProps) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  return (
    <CommonModal size="m" onClose={onClose}>
      <div className="p-2">
        <ModalTitle title={"게시글 작성"} />

        <TextArea
          size="m"
          label="제목"
          placeholder="제목을 입력하세요"
          disabled={false}
          value={title}
          setValue={setTitle}
        />
        <TextArea
          size="m"
          label="내용"
          placeholder="내용을 입력하세요"
          disabled={false}
          value={content}
          setValue={setContent}
        />
      </div>

      <div className="flex justify-end mr-[15px]">
        <Button
          color="theme"
          size="s"
          disabled={false}
          text="작성"
          type="button"
          onClick={onClose}
        />
      </div>
    </CommonModal>
  );
}

export default PostWrite;
