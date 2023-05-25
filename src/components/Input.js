const Input = ({ type, id, onChange, value }) => {
  return (
    <input
      type={type ? type : 'text'}
      className="form-control"
      id={id && id}
      value={value}
      onChange={(e) => onChange(e)}
    />
  );
};

export default Input;
