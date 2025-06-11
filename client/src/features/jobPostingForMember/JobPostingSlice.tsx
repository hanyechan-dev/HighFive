import { createSlice, type PayloadAction } from '@reduxjs/toolkit';

interface JobPostingFilter {
    careerType: string;
    educationLevel: string;
    workLocation: string; 
    job: string; 
    salary: string;
}

interface JobPostingFilterState {
  filter: JobPostingFilter;
}

const initialState: JobPostingFilterState = {
  filter: {
    careerType: '',
    educationLevel: '',
    workLocation: '',
    job: '',
    salary: '0',
  },
};

const jobPostingFilterSlice = createSlice({
  name: 'jobPostingFilter',
  initialState,
  reducers: {
    setJobPostingFilter(state, action: PayloadAction<JobPostingFilter>) {
      state.filter = action.payload;
    },
  },
});

export const { setJobPostingFilter } = jobPostingFilterSlice.actions;
export default jobPostingFilterSlice.reducer;