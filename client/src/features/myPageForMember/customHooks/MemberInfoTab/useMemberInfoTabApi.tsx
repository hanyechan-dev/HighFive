import { printErrorInfo } from "../../../../common/utils/ErrorUtil";
import type { MemberMyPageResponseDto, MemberResponseDto, MemberUpdateDto, MyPageResponseDto, MyPageUpdateDto, PasswordUpdateDto } from "../../props/myPageForMemberProps";
import { useMemberInfoTabController } from "./useMemberInfoTabController";
import { store } from "../../../../common/store/store";
import AuthUtil from "../../../../common/utils/AuthUtil";
import { deactivateAccountApi, readMyPageApi, updateMyNickNameApi, updateMyPageApi, updatePasswordApi } from "../../apis/MyPageForMemberApi";

export const useMemberInfoTabApi = () => {
    const { memberMyPageResponseDto,
        setMemberMyPageResponseDto,
    } = useMemberInfoTabController();

    const readMyPage = async () => {
        try {
            const memberMyPageResponseDto = (await readMyPageApi()).data;
            setMemberMyPageResponseDto(memberMyPageResponseDto);
        } catch (err) {
            printErrorInfo(err);
        }

    }

    const updateNickName = async (memberUpdateDto: MemberUpdateDto) => {
        try {
            const memberResponseDto: MemberResponseDto = (await updateMyNickNameApi(memberUpdateDto)).data;
            setMemberMyPageResponseDto({ memberResponseDto })
        } catch (err) {
            printErrorInfo(err);
        }
    }

    const updateMyPage = async (myPageUpdateDto: MyPageUpdateDto) => {
        try {
            const myPageResponseDto: MyPageResponseDto = (await updateMyPageApi(myPageUpdateDto)).data;
            setMemberMyPageResponseDto(
                {
                    ...memberMyPageResponseDto,
                    myPageResponseDto,
                }
            )
        } catch (err) {
            printErrorInfo(err);
        }
    }

    const updatePassword = async (passwordUpdateDto: PasswordUpdateDto) => {
        try {
            await updatePasswordApi(passwordUpdateDto);
        } catch (err) {
            printErrorInfo(err);
        }
    }

    const deactivateAccount = async () => {
        try {
            const accessToken = store.getState().auth.accessToken;

            if (!accessToken) {
                alert("로그인이 필요합니다.");
                return;
            }
            const id = AuthUtil.getIdFromToken(accessToken);
            if (id !== null) {
                await deactivateAccountApi(id);
            }
        } catch (err) {
            printErrorInfo(err);
        }
    }

    return {
        readMyPage,
        updateNickName,
        updateMyPage,
        updatePassword,
        deactivateAccount,
    }


}