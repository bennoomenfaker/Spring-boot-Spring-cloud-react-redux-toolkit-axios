import React, { useEffect , useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getAllProducts, deleteProduct, getProductById , updateProduct ,createProduct} from '../redux/features/productSlice';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';


function ListProduct() {
  const dispatch = useDispatch();
  const products = useSelector((state) => state.products.products);
  const [deleteProductId, setDeleteProductId] = useState(null);
  const [showDeleteModal, setShowDeleteModal] = useState(false);


  const showDeleteConfirmation = (id) => {
    setDeleteProductId(id);
    setShowDeleteModal(true);
  };
  

  useEffect(() => {
    dispatch(getAllProducts());
  }, [dispatch]);

  const   confirmDelete = async () => {
     await dispatch(deleteProduct(deleteProductId));
    await dispatch(getAllProducts());
    setShowDeleteModal(false);
    setDeleteProductId(null);
    
  };




  const [editProduct, setEditProduct] = useState(null);
  const [showEditModal, setShowEditModal] = useState(false);
  const [editedName, setEditedName] = useState('');
  const [editedQuantity, setEditedQuantity] = useState('');
  const [editedPrice, setEditedPrice] = useState('');

  

  const openEditModal = (product) => {
    setEditProduct(product);
    setEditedName(product.name);
        setEditedPrice(product.price);
    setShowEditModal(true);
  };
  

  const saveChanges = async () => {
    if (editProduct) {
       await dispatch(
        updateProduct({
          id: editProduct.id,
          name: editedName,
        
          price: editedPrice,
        })
      );
      await dispatch(getAllProducts())
      setShowEditModal(false);
      // Réinitialisez les états d'édition au besoin
      setEditProduct(null);
      setEditedName('');
     
      setEditedPrice('');
    }
  };



  





  const [newProduct, setNewProduct] = useState({
    name: '',
    price: '',
 
  });

  const [showNewProductModal, setShowNewProductModal] = useState(false);

  const handleNewProductChange = (e) => {
    const { name, value } = e.target;
    setNewProduct({ ...newProduct, [name]: value });
  };

  const handleCreateProduct = async () => {
    await dispatch(createProduct(newProduct));
    await dispatch(getAllProducts());
    setShowNewProductModal(false);
    setNewProduct({
      name: '',
      price: '',
     
    });
  };



  

  return (
<div className='div'>
<div className="nav">
    
<Button variant="primary" onClick={() => setShowNewProductModal(true)} className='btnn'> 
        Créer un nouveau produit
      </Button>
</div>
    
    <Table striped bordered hover className='table'>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Price</th>
        
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {products.map((product) => (
          <tr key={product.id}>
            <td>{product.id}</td>
            <td>{product.name}</td>
            <td>{product.price}</td>
         
            <td className='b'>
            <div style={{ display: 'flex',marginLeft:'20%' }}>
            <Button  style={{ width: '100px', marginRight: '5%' }}variant="primary" onClick={() => openEditModal(product)}>
             Editer
            </Button>

              <Button  style={{ width: '100px' }} variant="danger" onClick={() => showDeleteConfirmation(product.id)}>
                 Supprimer
              </Button>
         </div>
              
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
    {/* Modal de suppression */}
    <Modal show={showDeleteModal} onHide={() => setShowDeleteModal(false)}>
    <Modal.Header closeButton>
      <Modal.Title>Confirmation de suppression</Modal.Title>
    </Modal.Header>
    <Modal.Body>
      Êtes-vous sûr de vouloir supprimer ce produit ?
    </Modal.Body>
    <Modal.Footer>
      <Button variant="secondary" onClick={() => setShowDeleteModal(false)}>
        Annuler
      </Button>
      <Button variant="danger" onClick={confirmDelete}>
        Supprimer
      </Button>
    </Modal.Footer>
  </Modal>





  <Modal show={showEditModal} onHide={() => setShowEditModal(false)}>
  <Modal.Header closeButton>
    <Modal.Title>Modifier le produit</Modal.Title>
  </Modal.Header>
  <Modal.Body>
    <form>
      <div className="mb-3">
        <label htmlFor="editedName" className="form-label">
          Nom du produit
        </label>
        <input
          type="text"
          className="form-control"
          id="editedName"
          value={editedName}
          onChange={(e) => setEditedName(e.target.value)}
        />
      </div>
      
      <div className="mb-3">
        <label htmlFor="editedPrice" className="form-label">
          Prix
        </label>
        <input
          type="number"
          className="form-control"
          id="editedPrice"
          value={editedPrice}
          onChange={(e) => setEditedPrice(e.target.value)}
        />
      </div>
    </form>
  </Modal.Body>
  <Modal.Footer >
    <Button variant="secondary" onClick={() => setShowEditModal(false)}>
      Annuler
    </Button>
    <Button variant="primary" onClick={saveChanges}>
      Sauvegarder
    </Button>
  </Modal.Footer>
</Modal>




{/* Modal pour créer un nouveau produit */}
<Modal show={showNewProductModal} onHide={() => setShowNewProductModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Créer un nouveau produit</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <form>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Nom du produit
              </label>
              <input
                type="text"
                className="form-control"
                id="name"
                name="name"
                value={newProduct.name}
                onChange={handleNewProductChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="price" className="form-label">
                Prix
              </label>
              <input
                type="number"
                className="form-control"
                id="price"
                name="price"
                value={newProduct.price}
                onChange={handleNewProductChange}
              />
            </div>
      
          </form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowNewProductModal(false)}>
            Annuler
          </Button>
          <Button variant="primary" onClick={handleCreateProduct}>
            Créer
          </Button>
        </Modal.Footer>
      </Modal>

</div>




  );


  
}

export default ListProduct;