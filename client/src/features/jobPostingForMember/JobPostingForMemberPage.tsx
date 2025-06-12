import { useEffect, useState } from "react";
import CommonPage from "../../common/pages/CommonPage";
import JobPostingList from "./jobPostingForMemberComponent/JobPostingList";
import JobPostingMainCard from "./jobPostingForMemberComponent/JobPostingMainCard";
import JobPostingUnderCard from "./jobPostingForMemberComponent/JobPostingUnderCard";
import { JobPostingDetailApi, JobPostingMainCardForMemberApi, JobPostingUnderCardForMemberApi } from "./JobPostingForMemberApi";
import JobPostingDetailForMemberModal from "./JobPostingDetailForMemberModal";

interface JobPostingMainCardProps {
    id: number;
    title: string;
    companyName: string;
    companyType: string;
    job: string;
    workLocation: string;
    careerType: string;
    educationLevel: string;
    similarityScore: number | null;
    createdDate: string;
    imageUrl: string;
}

interface JobPostingUnderCardProps {
    id: number;
    title: string;
    companyName: string;
    companyType: string;
    job: string;
    workLocation: string;
    careerType: string;
    educationLevel: string;
    similarityScore: number | null;
    createdDate: string;
}

const JobPostingForMemberPage = () => {

    const [mainCards, setMainCards] = useState<JobPostingMainCardProps[]>([]);
    const [underCards, setUnderCards] = useState<JobPostingUnderCardProps[]>([]);
    const [showJobPostingDetailModal, setShowJobPostingDetailModal] = useState(false);


    useEffect(() => {
        const fetchMain = async () => {
            try {
                const res = await JobPostingMainCardForMemberApi();
                const mainCards = res.data.content;
                setMainCards(mainCards);
            } catch (err) {
                console.error(err);
            }
        };

        const fetchUnder = async () => {
            try {
                const res = await JobPostingUnderCardForMemberApi();
                const underCards = res.data.content;
                setUnderCards(underCards);
            } catch (err) {
                console.error(err);
            }
        };

        fetchMain();
        fetchUnder();

    }, []);

    const onClick = async (id: number) => {
        const res = await JobPostingDetailApi(id);
        const jobPosting = res.data.content;
        setShowJobPostingDetailModal(true);

    };



    return (
        <>
            {showJobPostingDetailModal && (
                <JobPostingDetailForMemberModal id={0} title={""} companyType={""} workingHours={""} workLocation={""} job={""} careerType={""} educationLevel={""} salary={0} content={""} requirement={""} imageUrls={[]} />
            )}
            <CommonPage >
                <div className="flex mt-2">
                    {mainCards.map(mainCard => (
                        <div key={mainCard.id}>
                            <JobPostingMainCard id={mainCard.id}
                                title={mainCard.title}
                                companyName={mainCard.companyName}
                                companyType={mainCard.companyType}
                                job={mainCard.job}
                                workLocation={mainCard.workLocation}
                                careerType={mainCard.careerType}
                                educationLevel={mainCard.educationLevel}
                                similarityScore={mainCard.similarityScore}
                                createdDate={mainCard.createdDate}
                                imageUrl={mainCard.imageUrl}
                                onClick={() => { onClick(mainCard.id) }} />
                        </div>

                    ))}
                </div>

                <div className="flex mt-2">
                    {underCards.map(underCard => (
                        <div key={underCard.id}>
                            <JobPostingUnderCard id={underCard.id}
                                title={underCard.title}
                                companyName={underCard.companyName}
                                companyType={underCard.companyType}
                                job={underCard.job}
                                workLocation={underCard.workLocation}
                                careerType={underCard.careerType}
                                educationLevel={underCard.educationLevel}
                                similarityScore={underCard.similarityScore}
                                createdDate={underCard.createdDate}
                                onClick={() => { onClick(underCard.id) }} />
                        </div>

                    ))}
                </div>

                <JobPostingList />

            </CommonPage>
        </>
    );

}

export default JobPostingForMemberPage