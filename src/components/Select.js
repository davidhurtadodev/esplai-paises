const Select = ({ children, onChange, value }) => {
  console.log(children);
  return (
    <select class="form-select" value={value} onChange={(e) => onChange(e)}>
      {children}
    </select>
  );
};

export default Select;
