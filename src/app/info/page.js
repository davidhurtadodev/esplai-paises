const Info = () => {
  return (
    <div classNameName="">
      <div className="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
        <button
          className="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center"
          id="bd-theme"
          type="button"
          aria-expanded="false"
          data-bs-toggle="dropdown"
          aria-label="Toggle theme (auto)"
        >
          <svg className="bi my-1 theme-icon-active" width="1em" height="1em">
            <use href="#circle-half"></use>
          </svg>
          <span className="visually-hidden" id="bd-theme-text">
            Toggle theme
          </span>
        </button>
        <ul
          className="dropdown-menu dropdown-menu-end shadow"
          aria-labelledby="bd-theme-text"
        >
          <li>
            <button
              type="button"
              className="dropdown-item d-flex align-items-center"
              data-bs-theme-value="light"
              aria-pressed="false"
            >
              <svg
                className="bi me-2 opacity-50 theme-icon"
                width="1em"
                height="1em"
              >
                <use href="#sun-fill"></use>
              </svg>
              Light
              <svg className="bi ms-auto d-none" width="1em" height="1em">
                <use href="#check2"></use>
              </svg>
            </button>
          </li>
          <li>
            <button
              type="button"
              className="dropdown-item d-flex align-items-center"
              aria-pressed="false"
            >
              <svg
                className="bi me-2 opacity-50 theme-icon"
                width="1em"
                height="1em"
              >
                <use href="#moon-stars-fill"></use>
              </svg>
              Dark
              <svg className="bi ms-auto d-none" width="1em" height="1em">
                <use href="#check2"></use>
              </svg>
            </button>
          </li>
          <li>
            <button
              type="button"
              className="dropdown-item d-flex align-items-center active"
              data-bs-theme-value="auto"
              aria-pressed="true"
            >
              <svg
                className="bi me-2 opacity-50 theme-icon"
                width="1em"
                height="1em"
              >
                <use href="#circle-half"></use>
              </svg>
              Auto
              <svg className="bi ms-auto d-none" width="1em" height="1em">
                <use href="#check2"></use>
              </svg>
            </button>
          </li>
        </ul>
      </div>

      <header className="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
        <a className="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="#">
          Environment App
        </a>
        <button
          className="navbar-toggler position-absolute d-md-none collapsed"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#sidebarMenu"
          aria-controls="sidebarMenu"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="navbar-nav">
          <div className="nav-item text-nowrap">
            <a className="nav-link px-3" href="#">
              Log out
            </a>
          </div>
        </div>
      </header>

      <div className="container-fluid">
        <div className="row">
          <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
              <h1 className="h2">Bienvenido</h1>
              <div className="btn-toolbar mb-2 mb-md-0">
                <div className="btn-group me-2"></div>
                <button
                  type="button"
                  className="btn btn-sm btn-outline-secondary dropdown-toggle"
                >
                  Selecciona un país
                </button>
              </div>
            </div>

            <canvas
              className="my-4 w-100"
              id="myChart"
              width="900"
              height="380"
            ></canvas>

            <h2>Datos de tu país</h2>
            <div className="table-responsive">
              <div className="col-md-2"></div>
              <div className="col-12 col-md-8">
                <table className="table table-striped table-sm">
                  <thead>
                    <tr>
                      <th scope="col">Country</th>
                      <th scope="col">Afghanistan</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Region</td>
                      <td>Middle East/Central Asia</td>
                    </tr>
                    <tr>
                      <td>Population (millions)</td>
                      <td>29.82</td>
                    </tr>
                    <tr>
                      <td>HDI</td>
                      <td>0.46</td>
                    </tr>
                    <tr>
                      <td>GDP per Capita</td>
                      <td>$614.66</td>
                    </tr>
                    <tr>
                      <td>Cropland Footprint</td>
                      <td>0.3</td>
                    </tr>
                    <tr>
                      <td>Grazing Footprint</td>
                      <td>0.2</td>
                    </tr>
                    <tr>
                      <td>Forest Footprint</td>
                      <td>0.08</td>
                    </tr>
                    <tr>
                      <td>Carbon Footprint</td>
                      <td>0.18</td>
                    </tr>
                    <tr>
                      <td>Fish Footprint</td>
                      <td>0</td>
                    </tr>
                    <tr>
                      <td>Total Ecological Footprint</td>
                      <td>0.79</td>
                    </tr>
                    <tr>
                      <td>Cropland</td>
                      <td>0.24</td>
                    </tr>
                    <tr>
                      <td>Grazing Land</td>
                      <td>0.2</td>
                    </tr>
                    <tr>
                      <td>Forest Land</td>
                      <td>0.02</td>
                    </tr>
                    <tr>
                      <td>Fishing Water</td>
                      <td>0</td>
                    </tr>
                    <tr>
                      <td>Urban Land</td>
                      <td>0.04</td>
                    </tr>
                    <tr>
                      <td>Total Biocapacity</td>
                      <td>0.5</td>
                    </tr>
                    <tr>
                      <td>Biocapacity Deficit or Reserve</td>
                      <td>-0.3</td>
                    </tr>
                    <tr>
                      <td>Earths Required</td>
                      <td>0.46</td>
                    </tr>
                    <tr>
                      <td>Countries Required</td>
                      <td>1.6</td>
                    </tr>
                    <tr>
                      <td>Data Quality</td>
                      <td>6</td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div className="col-md-2"></div>
            </div>
          </main>
        </div>
      </div>
    </div>
  );
};

export default Info;
