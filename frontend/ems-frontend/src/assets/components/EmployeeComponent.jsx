import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { createEmployee, getEmployee, updateEmployee } from '../../services/EmployeeService';

const EmployeeComponent = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });

  const {id} = useParams()
  const [errors, setErrors] = useState({});
  const navigator = useNavigate();

  useEffect(()=>{
    if(id)
    {
      getEmployee(id).then((response)=>{
        setFormData(response.data)
      }).catch((error)=>{console.error(error);})
    }
  },[id]);
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const pageTitle = id ? 'Update Employee' : 'Add Employee';

  const validate = () => {
    const newErrors = {};
    if (!formData.firstName.trim()) newErrors.firstName = 'First name is required';
    if (!formData.lastName.trim()) newErrors.lastName = 'Last name is required';
    if (!formData.email.trim()) {
      newErrors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = 'Email is invalid';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const saveOrUpdateEmployee = (e) => {
    e.preventDefault();
    if (!validate()) return;
    if(id){
      updateEmployee(id, formData).then((response)=>{
        console.log(response.data);
        navigator('/employees');
      }).catch(error=>{
        console.error(error);
      })
    }else{
      //console.log("Submitted:", formData);
    createEmployee(formData).then((response) => {console.log(response.data);
    navigator('/employees')
    }).catch(error=>{console.error(error);})
    
    }
    
  };

  return (
    <div className="d-flex flex-column min-vh-100 bg-light">
      <div className="flex-grow-1 d-flex justify-content-center align-items-center">
        <div className="w-50 shadow p-4 rounded bg-white">
          <h2 className="mb-4 text-center">{pageTitle}</h2>
          <form onSubmit={saveOrUpdateEmployee} noValidate>
            <div className="mb-3">
              <label htmlFor="firstName" className="form-label">First Name</label>
              <input
                type="text"
                className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                id="firstName"
                name="firstName"
                value={formData.firstName}
                onChange={handleChange}
                placeholder="Enter first name"
              />
              {errors.firstName && (
                <div className="invalid-feedback">{errors.firstName}</div>
              )}
            </div>

            <div className="mb-3">
              <label htmlFor="lastName" className="form-label">Last Name</label>
              <input
                type="text"
                className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                id="lastName"
                name="lastName"
                value={formData.lastName}
                onChange={handleChange}
                placeholder="Enter last name"
              />
              {errors.lastName && (
                <div className="invalid-feedback">{errors.lastName}</div>
              )}
            </div>

            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email address</label>
              <input
                type="email"
                className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Enter email"
              />
              {errors.email && (
                <div className="invalid-feedback">{errors.email}</div>
              )}
            </div>

            <button type="submit" className="btn btn-primary w-100">Submit</button>
          </form>
        </div>
      </div>

    </div>
  );
};

export default EmployeeComponent;
