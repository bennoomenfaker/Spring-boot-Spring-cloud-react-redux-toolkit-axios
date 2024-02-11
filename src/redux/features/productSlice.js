import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const API_URL_PRODUCT =  'http://localhost:9999/company-service/companies';


export const getAllProducts = createAsyncThunk('product/getAllProducts', async () => {
    

  const response = await axios.get(`${API_URL_PRODUCT}`);
 
  return response.data;

  });


 // Action pour delete un produit
  export const deleteProduct = createAsyncThunk(
    'product/deleteProduct',
    async (id) => {
      console.log(id);
      await axios.delete(`${API_URL_PRODUCT}/${id}`);
      return id;
    }
  );
  
  // Action pour créer un produit
  export const createProduct = createAsyncThunk(
    'product/createProduct',
    async ({ name, price }, { rejectWithValue }) => {
      try {
        const response = await axios.post(
          `${API_URL_PRODUCT}`,
          { name, price }
        );
        console.log(response);
        return response.data;
      } catch (error) {
        if (!error.response) {
          throw error;
        }
        return rejectWithValue(error.response.data);
      }
    }
  );




  export const updateProduct = createAsyncThunk(
    'product/updateProduct',
    async ({ id, name, price }, thunkAPI) => {
      try {
        const response = await axios.put(
          `${API_URL_PRODUCT}/${id}`, // Utilisez le bon point de terminaison ici
          { name, price }
        );
        return response.data;
      } catch (error) {
        return thunkAPI.rejectWithValue(error.response.data);
      }
    }
  );
  
//get by id category
export const getProductById = createAsyncThunk('product/getProductById', async (id) => {
 
  const response = await axios.get(`${API_URL_PRODUCT}/${id}`);
  return response.data;
});

  
export const productSlice = createSlice({
  name: 'products',
  initialState: {
   
    products : [],
    
   
    isLoading: false,
    error: null,
    
    lenProducts : null,
    
 
   
  },
  reducers: {},
  extraReducers: (builder) => {
    //create category
    builder.addCase(createProduct.pending, (state) => {
      state.isLoading = true;
    });
    builder.addCase(createProduct.fulfilled, (state, action) => {
      state.isLoading = false;
      
    });
    builder.addCase(createProduct.rejected, (state, action) => {
      state.isLoading = false;
      state.error = action.error.message;
    });
    //recpere all

    builder.addCase(getAllProducts.pending, (state) => {
      state.isLoading = true;
    });
    builder.addCase(getAllProducts.fulfilled, (state, action) => {
      state.isLoading = false;
     // console.log(action.payload)
      state.products = action.payload;
      state.lenProducts = action.payload.length;
    });
    builder.addCase(getAllProducts.rejected, (state, action) => {
      state.isLoading = false;
      state.error = action.error.message;
    });
    //delete categorie
         builder.addCase(deleteProduct.pending, (state) => {
          state.isLoading = true;
        });
        builder.addCase(deleteProduct.fulfilled, (state, action) => {
          state.isLoading = false;
          const deletedProductId = action.payload;
          state.products = state.products.filter(d => d._id !== deletedProductId);
        });
        builder.addCase(deleteProduct.rejected, (state, action) => {
          state.isLoading = false;
          state.error = action.error.message;
        });

     
      
      // update category
builder.addCase(updateProduct.pending, (state) => {
  state.isLoading = true;
});
builder.addCase(updateProduct.fulfilled, (state, action) => {
  state.isLoading = false;
 
});
builder.addCase(updateProduct.rejected, (state, action) => {
  state.isLoading = false;
  state.error = action.error.message;
});

 
    


  },
})

export default productSlice.reducer;
export const selectProduct = (state) => state.products.products;
export const lenCategorie = (state) => state.products.lenProducts;