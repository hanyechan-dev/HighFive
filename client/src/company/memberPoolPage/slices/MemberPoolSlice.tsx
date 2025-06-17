import { createSlice, type PayloadAction } from '@reduxjs/toolkit';



export interface MemberFilter {
    hasCareer: boolean;       
    educationLevel: string;    
    address: string;           
    job: string;               
}

export interface MemberPoolFilterState {
    filter: MemberFilter;
}

const initialState: MemberPoolFilterState = {
    filter: {
        hasCareer: false, 
        educationLevel: '',   
        address: '',         
        job: ''     
    },
};

const memberPoolFilterSlice = createSlice({
    name: 'memberPoolFilter',
    initialState,
    reducers: {
        setMemberPoolFilter(state, action: PayloadAction<MemberFilter>) {
            
            state.filter = action.payload;
        }
        
    },
});

export const { setMemberPoolFilter } = memberPoolFilterSlice.actions;
export default memberPoolFilterSlice.reducer;